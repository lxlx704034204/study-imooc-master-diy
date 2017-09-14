package com.myimooc.auth.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myimooc.auth.common.AjaxResult;
import com.myimooc.auth.common.tree.Node;
import com.myimooc.auth.common.tree.Tree;
import com.myimooc.auth.context.NativeCache;
import com.myimooc.auth.entity.Function;
import com.myimooc.auth.service.FunctionService;

/**
 * 功能控制器
 * @author zc on 2017-08-20
 */
@Controller
@RequestMapping("/function")
public class FunctionController {
	
	@Autowired
	private FunctionService functionService;
	@Autowired
	private NativeCache nativeCache;
	
	@RequestMapping("/index")
	public String menuList(){
		return "/security/funciont/function_list";
	}
	
	@PostMapping("/addEditFunction")
	@ResponseBody
	public AjaxResult addEditFunction(Function function){
		if(StringUtils.isEmpty(function.getId())){
			function.setSerialNum(nativeCache.getFunctions().size());
			functionService.addFunction(function);
			nativeCache.addFunction(function);
		}else{
			functionService.updateUrl(function.getId(), function.getUrl());
			nativeCache.replaceFunction(function);
		}
		return AjaxResult.success();
	}
	
	@PostMapping("/deleteFunction")
	@ResponseBody
	public AjaxResult deleteFunctionById(Long id){
		functionService.deleteFunctionById(id);
		nativeCache.removeFunction(id);
		return AjaxResult.success();
	}
	
	@PostMapping("/getSubFunctions")
	@ResponseBody
	public List<Function> getSubFunctions(Integer page,Integer rows,Long parentId){
		if(Objects.equals(null, parentId)){
			parentId = 0L;
		}
		return functionService.getFunctions(page, rows, parentId);
	}
	
	@PostMapping("/buildFunctionTreeForEdit")
	@ResponseBody
	public List<Node> buildMenuTreeForEdit(){
		List<Function> list = nativeCache.getFunctions();
		return new Tree(list).build();
	}
}
