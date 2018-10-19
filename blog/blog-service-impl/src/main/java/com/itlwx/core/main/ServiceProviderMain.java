package com.itlwx.core.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Properties;

import com.itlwx.common.utils.PropertyGetter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

public class ServiceProviderMain {

	// log4j 日志初始化
	static {
		try {
			URL url = ResourceUtils.getURL("classpath:log4j.properties");
			PropertyConfigurator.configure(url);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static final Log log = LogFactory.getLog(ServiceProviderMain.class);
	private static final String SHUTDOWN_CMD = "stop well service";
	private static final String SHUTDOWN_ACK_OK = "ok";
	private static final String SHUTDOWN_ACK_FAIL = "fail";
	private static volatile boolean isRun = true;

	/*初始化spring容器*/
	private static void contextInitialized() {
		String[] fn = new String[] { "spring/spring-config.xml" };
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(fn);

		/*创建服务提供者线程*/
		Thread serverThread = new Thread(new SocketServiceThread(ctx));
		serverThread.start();
	}

	/**
	 * dubbo服务应用启动入口
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		if (ObjectUtils.isEmpty(args) || StringUtils.isEmpty(args[0])) {
			// 默认重启模式
			stopService();
			startService();
		} else {
			/*根据命令执行操作*/
			if ("start".equals(args[0])) {
				startService();
			} else if ("stop".equals(args[0])) {
				stopService();
			} else if ("restart".equals(args[0])) {
				stopService();
				startService();
			} else {
				stopService();
				startService();
			}
		}
	}

	private static void startService() {
		log.info("==============blog service begin start ===========");
		contextInitialized();
		/*添加一个钩子，当jvm关闭前，先执行这个钩子*/
		Runtime.getRuntime().addShutdownHook(new ServiceBizShutdownHook());
		log.info("==============blog service started ==============");
	}

	public static void stopService() {
		log.info("blog service begin stop !!");
		InputStream is = null;
		Socket socket = null;
		String host = "";
		int port = 0;
		try {
			Properties properties = new Properties();
			is = new ClassPathResource("resource/dubbo.properties").getInputStream();
			properties.load(is);
			host = properties.getProperty("dubbo.host");
			port = Integer.parseInt(properties.getProperty("dubbo.stopPort"));
			try {
				socket = new Socket(host, port);
			} catch (ConnectException e) {
				log.warn("blog service is not running, " + e.getMessage());
				return;
			}
			OutputStream outputStream = socket.getOutputStream();
			/*发送关闭命令到服务线程*/
			outputStream.write(SHUTDOWN_CMD.getBytes());
			outputStream.flush();
			outputStream.close();
			if (!socket.isClosed()) {
				InputStream inputStream = socket.getInputStream();
				if (SHUTDOWN_ACK_OK.equals(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")))) {
					log.info("blog service stoped !!");
				} else {
					log.warn("blog service stop fail.");
				}
				inputStream.close();
				socket.close();
			}
		} catch (NumberFormatException e) {
			log.error("blog service dubbo.stopPort config error, please check stopPort: " + port);
		} catch (UnknownHostException e) {
			log.error("blog service dubbo.host config error, please check host: " + host);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}

	static class SocketServiceThread implements Runnable {

		private ClassPathXmlApplicationContext ctx;

		public SocketServiceThread(ClassPathXmlApplicationContext ctx) {
			this.ctx = ctx;
		}

		public void run() {
			int port = 0;
			try {
				port = Integer.parseInt(PropertyGetter.getProperty("dubbo.stopPort"));
				/*创建socket服务端，用于监听用户动作，以便操作整个服务的重启、关闭*/
				ServerSocket serverSocket = new ServerSocket(port);
				while (isRun) {
					// 单线程socket，不能同时执行关闭服务
					Socket socket = serverSocket.accept();
					InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();
					String cmd = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
					if (SHUTDOWN_CMD.equals(cmd)) {
						/*关闭spring容器*/
						isRun = false;
						ctx.close();
						/*发送消息*/
						outputStream.write(SHUTDOWN_ACK_OK.getBytes());
						socket.close();
						serverSocket.close();
					} else {
						outputStream.write(SHUTDOWN_ACK_FAIL.getBytes());
					}
					inputStream.close();
					outputStream.close();
				}
			} catch (NumberFormatException e) {
				log.error("blog service dubbo.stopPort config error, please check stopPort: " + port);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	static class ServiceBizShutdownHook extends Thread {
		@Override
		public void run() {
			log.info("blog service stop success!!");
		}
	}

}
