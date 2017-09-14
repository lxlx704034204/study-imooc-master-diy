package com.myimooc.sso.demo1.x.com;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myimooc.sso.util.HttpUtils;
import com.myimooc.sso.util.RespMessage;

/**
 * 
 * @author ZhangCheng
 * @date 2017-04-02
 * @version V1.0
 */
@Controller
public class DemoOneController {

    // demo1.x.com:8080/demo1 (同父域情况下的SSO效果演示不能用http://localhost:8080/demo1 )
    @RequestMapping("/demo1")
    public ModelAndView main(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        
        //校验cookie是否为空
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            //校验cookie是否存在
            for(Cookie cookie : cookies){
                if("ssocookie".equals(cookie.getName())){
                    //向校验服务器发送校验请求
                    String url = "http://check.x.com:8080/sso/checkCookie";
                    RespMessage respMessage = HttpUtils.doGet(url, cookie.getName(), cookie.getValue());
                    if("200".equals(respMessage.getRespCode())){
                        mv.setViewName("demo1");
                        return mv;
                    }
                }
            }
        }
        mv.addObject("gotoUrl", "http://demo1.x.com:8080/demo1");
        mv.setViewName("login");
        return mv;
    }
}
