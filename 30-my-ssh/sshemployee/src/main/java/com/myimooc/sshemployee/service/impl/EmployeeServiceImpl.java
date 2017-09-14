package com.myimooc.sshemployee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myimooc.sshemployee.dao.EmployeeDao;
import com.myimooc.sshemployee.domain.Employee;
import com.myimooc.sshemployee.domain.PageBean;
import com.myimooc.sshemployee.service.EmployeeService;

/**
 * 员工管理的业务层的实现类
 * @author ZhangCheng on 2017-08-18
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	/**
	 * 业务层登录方法
	 */
	@Override
	public Employee login(Employee employee) {
		Employee existEmployee = employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}
	
	/**
	 * 分页查询员工信息
	 */
	@Override
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		// 封装当前页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示数据
		int begin = (currPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	/**
	 * 保存员工信息
	 */
	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
		
	}

	@Override
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}
	
	/**
	 * 修改员工信息
	 */
	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	/**
	 * 删除员工信息
	 */
	@Override
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
}
