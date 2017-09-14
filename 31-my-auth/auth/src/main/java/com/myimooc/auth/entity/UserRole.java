package com.myimooc.auth.entity;

import com.myimooc.auth.common.BaseEntity;

/**
 * 用户角色实体类
 * @author zc on 2017-08-19
 */
public class UserRole extends BaseEntity{
	
	private Long userId;
	private Long roleId;
	
	@Override
	public String toString() {
		return "UserRole [userId=" + userId + ", roleId=" + roleId + "]";
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	
	
}
