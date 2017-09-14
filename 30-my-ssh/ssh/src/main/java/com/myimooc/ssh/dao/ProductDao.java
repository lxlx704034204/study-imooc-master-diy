package com.myimooc.ssh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.myimooc.ssh.domain.Product;

/**
 * 商品管理的数据访问类
 * @author ZhangCheng on 2017-08-17
 */
@Repository
public class ProductDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 数据访问层保存商品的方法
	 */
	public void save(Product product) {
		System.out.println("Dao中的save方法执行了...");
		hibernateTemplate.save(product);
	}
}
