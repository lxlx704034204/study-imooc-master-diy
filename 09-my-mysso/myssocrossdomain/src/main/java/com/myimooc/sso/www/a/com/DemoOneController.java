package com.myimooc.sso.www.a.com;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myimooc.sso.util.RespMessage;
import com.myimooc.sso.util.HttpUtils;

/**
 * 
 * @author ZhangCheng
 * @date 2017-04-02
 * @version V1.0
 */
@Controller
@RequestMapping("/a")
public class DemoOneController {
    
    /** www.a.com/a/demo1
     * 跳转到demo1的主页
     * @param request
     * @return
     */
    @RequestMapping("/demo1")
    public ModelAndView demo1(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        
        //校验cookie是否为空
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){ //界面分流
            //校验cookie是否存在
            for(Cookie cookie : cookies){
                if("ssocookie".equals(cookie.getName())){
                    // 封装请求参数
                    Map<String,String> param = new HashMap<String,String>();
                    param.put("cookieName", cookie.getName());
                    param.put("cookieValue", cookie.getValue());
                    // 向校验服务器(LoginCheck.javaa) 发送校验请求
                    String url = "http://www.x.com/sso/checkCookie";
                    RespMessage respMessage = HttpUtils.doGet(url, param);
                    // 校验通过
                    if("200".equals(respMessage.getRespCode())){
                        mv.setViewName("demo1");//跳转到demo1.html
                        return mv;
                    }
                }
            }
        }
        // 登录失败重新登录
        String path = request.getContextPath();
        mv.addObject("contextPath",path);                                 //设置login.html中的 value="${contextPath}"
        mv.addObject("gotoUrl", "http://www.a.com/a/demo1");//设置login.html中的 value="${gotoUrl}"      //var gotoUrl= $("#gotoUrl_input").val(); //<input id="gotoUrl_input" type="hidden" name="gotoUrl" value="${gotoUrl}"/>
        mv.addObject("path","a");                           //...？？？
        mv.setViewName("login");//无效的cookies的话，则一直展示login.html界面
        return mv;
    }
    
    /** login.js 跳转
     * 用户登录
     * @param param
     * @return
     */
    @PostMapping(value="/doLogin")
    @ResponseBody
    public RespMessage doLogin(@RequestParam Map<String,String> param){
        // 向校验服务器发送校验请求
        String url = "http://www.x.com/sso/doLogin";
        RespMessage respMessage = HttpUtils.doGet(url, param);
        System.out.println("--->-DemoOneController-doLogin(...)-SSO服务器响应消息："+respMessage);//diy打印"SSO服务器响应消息："+respMessage
        return respMessage;
    }
    
    /**
     * 想当前域添加cookie
     * @param cookieName
     * @param cookieValue
     * @param response
     */
    @RequestMapping(value="/addCookie")
    public void addCookie(String cookieName,String cookieValue,HttpServletResponse response){
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
