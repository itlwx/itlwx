package com.itlwx.main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
/**
 * 自动生成相关bean和mapper的类
 * @author wz
 *
 */
public class GeneratorMain {

	public static void main(String[] args) {
		try {
			  List<String> warnings = new ArrayList<String>(); 
			  boolean overwrite = true; 
			  File configFile = new File("src/main/resources/mybatis/generator.xml"); 
			  ConfigurationParser cp = new ConfigurationParser(warnings); 
			  Configuration config = cp.parseConfiguration(configFile);
			  DefaultShellCallback callback = new DefaultShellCallback(overwrite); 
			  MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings); 
			  myBatisGenerator.generate(null); 
			  System.out.println("完成");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	}
}
