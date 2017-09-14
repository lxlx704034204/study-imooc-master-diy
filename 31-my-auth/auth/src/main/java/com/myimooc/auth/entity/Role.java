package com.myimooc.auth.entity;

import com.myimooc.auth.common.BaseEntity;

/**
 * 角色实体类
 * @author zc on 2017-08-19
 */
public class Role extends BaseEntity{
	
	private String name;
	private String functionIds;
	
	@Override
	public String toString() {
		return "Role [name=" + name + ", functionIds=" + functionIds + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFunctionIds() {
		return functionIds;
	}
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	
	
	
}
