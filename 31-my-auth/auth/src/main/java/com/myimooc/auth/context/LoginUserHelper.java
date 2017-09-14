package com.myimooc.auth.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myimooc.auth.common.Whether;
import com.myimooc.auth.dto.Accordion;
import com.myimooc.auth.entity.Function;
import com.myimooc.auth.entity.Role;
import com.myimooc.auth.entity.RoleFunction;
import com.myimooc.auth.entity.User;
import com.myimooc.auth.entity.UserRole;
import com.myimooc.auth.service.RoleService;
import com.myimooc.auth.service.UserService;

@Component
public class LoginUserHelper {
	
	@Autowired
	private UserService userService;
	@Autowired
	private NativeCache nativeCache;
	@Autowired
	private RoleService roleService;
	
	public void executeLogin(String name,String pwd){
		// 根据用户名和密码查询用户信息
		User user = userService.getUserByNameAndPwd(name, pwd);
		if(null == user){
			return;
		}
		
		List<UserRole> userRoles = userService.getUserRolesByUserId(user.getId());
		
		if(null == userRoles || 0 == userRoles.size()){
			return;
		}
		List<Long> roleIds = new ArrayList<Long>();
		for(UserRole ur:userRoles){
			roleIds.add(ur.getRoleId());
		}
		
		List<Role> roles = roleService.getRoles(roleIds);
		nativeCache.setRole(user.getId(), roles);
			
		LoginUserCache.put(user);
		List<Accordion> accordions = getAccordions(true,user.getId());
		LoginUserCache.setAccordions(user.getName(),accordions);
	}
	
	private List<Accordion> getAccordions(boolean isAdmin,Long userId){
		Set<String> permissionUrls = new HashSet<>();
		Set<Long> rootFunctionIdSet = new HashSet<>();
		
		if(!isAdmin){
			for(Role role:nativeCache.getRoles(userId)){
				List<RoleFunction> roleFunctions = roleService.getRoleFunctions(role.getId());
				
				for (RoleFunction rf : roleFunctions) {
					Function func = nativeCache.getFunction(rf.getFunctionId());
					if(Objects.equals(func.getAccordion(), Whether.YES.getValue())){
						rootFunctionIdSet.add(func.getId());
					}else{
						permissionUrls.add(func.getUrl());
					}
				}
			}
		}
		
		Map<Long,Accordion> accordionMap = new HashMap<Long,Accordion>();
		List<Accordion> permissionAccordionSet = new LinkedList<>();
		List<Function> founctions = nativeCache.getFunctions();
		List<Accordion> rootAccordionSet = new LinkedList<>();
		
		for(Function function : founctions){
			Accordion accordion = new Accordion(function.getId(),function.getParentId(),
					function.getName(),function.getUrl(),function.getSerialNum());
			accordionMap.put(function.getId(), accordion);
			if(!isAdmin){
				if(permissionUrls.contains(function.getUrl())){
					permissionAccordionSet.add(accordion);
				}
				if(rootFunctionIdSet.contains(function.getId())){
					rootAccordionSet.add(accordion);
				}
			}else{
				permissionAccordionSet.add(accordion);
				if(Whether.YES.getValue() == function.getAccordion()){
					rootAccordionSet.add(accordion);
				}
			}
			
		}
		
		Collections.sort(rootAccordionSet);
		Collections.sort(permissionAccordionSet);
		for (Accordion accordion : rootAccordionSet) {
			complateAccordion(permissionAccordionSet,accordion);
		}
		return rootAccordionSet;
		
	}

	private void complateAccordion(List<Accordion> permissionAccordionSet, Accordion rootAccordion) {
		for (Accordion accordion : permissionAccordionSet) {
			if(Objects.equals(accordion.getParentId(), rootAccordion.getId())){
				rootAccordion.getChildren().add(accordion);
			}
		}
	}
	
}
