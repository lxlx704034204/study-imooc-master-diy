package com.myimooc.identifying.controller;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myimooc.identifying.generator.Image;
import com.myimooc.identifying.generator.ImageResult;

/**
 * 登录控制器
 * @author ZhangCheng on 2017-07-09
 *
 */
@Controller
public class LoginController {
	
	/**
	 * 登录主页
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	public String identify(Model model,HttpServletRequest request,HttpServletResponse response){
		try{
			ImageResult imageResult  = Image.generateImage();
			model.addAttribute("file", imageResult.getName());
			model.addAttribute("tip", imageResult.getTip());
			System.out.println(imageResult.getName() + imageResult.getTip());
			Cookie cookie = new Cookie("note",imageResult.getUniqueKey());
			response.addCookie(cookie);
			request.getSession().setAttribute(imageResult.getUniqueKey(), imageResult);
		}catch(Exception e){
			System.out.println("获取图片失败");
			e.printStackTrace();
		}
		return "login";
	}
	
	 /**
     * 刷新图片
     *
     * @param request
     * @return
     * @throws IOException
     */
	@RequestMapping(value = "/getPng")
    @ResponseBody
    public String getPng(HttpServletRequest request) throws IOException{
        ImageResult imageResult = Image.generateImage();
        ((HttpServletRequest) request).getSession().setAttribute("imageResult", imageResult);
        return imageResult.getName() + "," + imageResult.getTip();
    }
	
    /**
     * 验证消息
     *
     * @param location
     * @param request
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("/dologin")
    @ResponseBody
    public String doLogin(String location, HttpServletRequest request, String userName, String password, RedirectAttributes redirectAttributes) {
        System.out.println("验证坐标："+ location);
    	Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		Cookie note = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("note")) {
                note = cookie;
                break;
            }
        }
        
        if(null == note){
        	return "ERROR";
        }
        
        ImageResult imageResult = (ImageResult)request.getSession().getAttribute(note.getValue());
        
        if(validate(location,imageResult)){
        	return "OK";
        }
        
        return "ERROR";
    }
    /**
     * 验证是否正确
     * @param locationString
     * @param imageResult
     * @return
     */
    private boolean validate(String locationString, ImageResult imageResult) {
		
    	String[] resultArray = locationString.split(";");
    	int[][] array = new int[resultArray.length][2];
    	for (int i = 0; i<resultArray.length;i++) {
			String[] temp = resultArray[i].split(",");
			array[i][0] = Integer.parseInt(temp[0]) + 150 - 10;
			array[i][1] = Integer.parseInt(temp[1]) + 300;
		}
    	
    	for(int i=0;i<array.length;i++){
    		int location = location(array[i][1],array[i][0]);
    		System.out.println("解析后的坐标序号：" + location);
    		if(!imageResult.getKeySet().contains(location)){
    			return false;
    		}
    	}
    	
		return true;
	}

	private int location(int x, int y) {
		if(y >=0 && y<75){
			return xLocation(x);
		}else if(y >=75 && y<=150){
			return xLocation(x)+4;
		}else{
			// 脏数据
			return -1;
		}
	}

	private int xLocation(int x) {
		if(x >=0 && x<75){
			return 0;
		}else if(x >=75 && x<150){
			return 1;
		}else if(x >=150 && x<225){
			return 2;
		}else if(x >=225 && x<=300){
			return 3;
		}else{
			// 脏数据
			return -1;
		}
	}
}
