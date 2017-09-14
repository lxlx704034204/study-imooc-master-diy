package com.myimooc.sso.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录校验工具类
 * @author ZhangCheng
 * @date 2017-03-22
 * @version V1.0
 */
public class LoginCheck {
    /** 测试用户名 */
    public static final String USERNAME="user";
    /** 测试密码*/
    public static final String PASSWORD="123";
    /** Cookie键 */
    public static final String COOKIE_NAME = "ssocookie";
    /** Cookie值*/
    public static final String COOKIE_VALUE = "sso";
    
    /**
     * 登录用户名和密码校验
     * @param username 用户名
     * @param password 密码
     * @return true用户名和密码正确；false用户名或密码错误
     */
    public static boolean checkLogin(String username,String password){
        if(USERNAME.equalsIgnoreCase(username) && 
            PASSWORD.equalsIgnoreCase(password)){
            return true;
        }
        return false;
    }
    
    /**
     * 校验Cookie
     * @param request
     * @return true正确；false错误
     */
    public static boolean checkCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if( cookies == null){
            return false;
        }
        for (Cookie cookie : cookies) {
            if(COOKIE_NAME.equals(cookie.getName()) && 
                COOKIE_VALUE.equals(cookie.getValue())){
                return true;
            }
        }
        return false;
    }
}
