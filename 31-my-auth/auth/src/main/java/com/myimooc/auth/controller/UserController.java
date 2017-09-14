package com.myimooc.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myimooc.auth.common.AjaxResult;
import com.myimooc.auth.entity.User;
import com.myimooc.auth.service.UserService;

/**
 * 用户控制器
 * @author zc on 2017-08-20
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index(){
		return "security/user/user_list";
	}
	
	@PostMapping("/getUsers")
	public List<User> getUsers(Integer page,Integer rows){
		return userService.getUsersByPage(page, rows);
	}
	
	@PostMapping("/addEditUser")
	@ResponseBody
	public AjaxResult addEditUser(User user){
		if(StringUtils.isEmpty(user.getId())){
			userService.save(user);
		}else{
			userService.update(user);
		}
		return AjaxResult.success();
	}
	
	@PostMapping("/deleteUser")
	@ResponseBody
	public AjaxResult deleteUser(Long id){
		userService.remove(id);
		return AjaxResult.success();
	}
}
