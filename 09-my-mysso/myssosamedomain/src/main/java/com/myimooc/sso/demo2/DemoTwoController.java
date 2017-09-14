package com.myimooc.sso.demo2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myimooc.sso.web.util.LoginCheck;

/**
 * 
 * @author ZhangCheng
 * @date 2017-04-02
 * @version V1.0
 */
@Controller
public class DemoTwoController {

    @RequestMapping("/demo2")
    public ModelAndView main(HttpServletRequest request) {
        
        ModelAndView mv = new ModelAndView();
        
        if (LoginCheck.checkCookie(request)) {
            mv.setViewName("demo2");
            return mv;
        }
        mv.addObject("gotoUrl", "/demo2");
        mv.setViewName("login");
        return mv;
    }
}
