package com.itlwx.web.cms.controller;
import com.itlwx.common.exception.ErrorCode;
import com.itlwx.core.bo.UserBO;
import com.itlwx.core.service.UserService;
import com.itlwx.web.BaseController;
import com.itlwx.web.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController {
    public static final String LOGIN_CODE = "LOGIN_CODE";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/index")
    public String toIndex(){
        return "index";
    }

    /**
     * 验证登录用户
     * @return
     */
    @RequestMapping(value = "/verify")
    public ModelAndView verify(String username, String password, String code){
        String loginCode = (String)getRequest().getSession().getAttribute(LOGIN_CODE);
        if(loginCode != null && loginCode.equals(code)){
            if(StringUtils.hasText(username) && StringUtils.hasText(password)){
                UserBO user = userService.verifyUser(username, password);
                if(user == null){
                    HttpResult.toError(ErrorCode.LOGIN_PARAM_ERROR,getResponse());
                }else {
                    setSessionUser(user);
                    getRequest().getSession().removeAttribute(LOGIN_CODE);
                    HttpResult.toSuccess(getResponse());
                }
            }else{
                HttpResult.toError(ErrorCode.LOGIN_PARAM_NULL, getResponse());
            }
        }else{
            HttpResult.toError(ErrorCode.VALID_CODE_ERROR, getResponse());
        }
        return null;
    }


    /**
     * 获取验证码图片
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Font mFont = new Font("Times New Roman", Font.PLAIN, 17);

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 表明生成的响应是图片
        response.setContentType("image/jpeg");

        int width = 80, height = 25;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(1, 1, width - 1, height - 1);
        g.setColor(new Color(102, 102, 102));
        g.drawRect(0, 0, width - 1, height - 1);
        g.setFont(mFont);

        g.setColor(getRandColor(160, 200));

        // 画随机线
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 从另一方向画随机线
        for (int i = 0; i < 70; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(12) + 1;
            int yl = random.nextInt(6) + 1;
            g.drawLine(x, y, x - xl, y - yl);
        }

        // 生成随机数,并将随机数字转换为字母
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110),
                    20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 15, 18);
        }
        request.getSession().setAttribute(LOGIN_CODE, sRand);
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
