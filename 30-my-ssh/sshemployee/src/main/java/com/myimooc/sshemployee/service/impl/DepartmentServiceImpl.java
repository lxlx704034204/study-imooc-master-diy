package com.myimooc.sshemployee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myimooc.sshemployee.dao.DepartmentDao;
import com.myimooc.sshemployee.domain.Department;
import com.myimooc.sshemployee.domain.PageBean;
import com.myimooc.sshemployee.service.DepartmentService;

/**
 * 部门管理的业务层的实现类
 * @author ZhangCheng on 2017-08-18
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 分页查询
	 */
	@Override
	public PageBean<Department> findByPage(Integer currPage) {
		PageBean<Department> pageBean = new PageBean<Department>();
		// 封装当前页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		int totalCount = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示数据
		int begin = (currPage - 1) * pageSize;
		List<Department> list = departmentDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	/**
	 * 添加部门
	 */
	@Override
	public void save(Department department) {
		departmentDao.save(department);
		
	}
	
	/**
	 * 根据部门ID查询部门信息
	 */
	@Override
	public Department findById(Integer did) {
		return departmentDao.findById(did);
	}
	
	/**
	 * 修改部门
	 */
	@Override
	public void update(Department department) {
		departmentDao.update(department);
		
	}

	/**
	 * 删除部门
	 */
	@Override
	public void delete(Department department) {
		departmentDao.delete(department);
		
	}
	
	/**
	 * 查询所有部门
	 */
	@Override
	public List<Department> findAll() {
		return departmentDao.findAll();
	} 
	
}
