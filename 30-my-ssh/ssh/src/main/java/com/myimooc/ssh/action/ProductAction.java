package com.myimooc.ssh.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myimooc.ssh.domain.Product;
import com.myimooc.ssh.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品管理的控制类
 * @author ZhangCheng on 2017-08-17
 */
@Controller
@Scope("prototype")
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	
	private static final long serialVersionUID = 1L;

	// struts和spring整合，按名称自动装配
	@Autowired
	private ProductService productService;
	
	// 模型驱动使用的类
	private Product product = new Product();
	
	@Override
	public Product getModel() {
		return product;
	}
	
	/**
	 * 保存商品的执行方法：save
	 */
	public String save(){
		System.out.println("Action中的save方法执行了...");
		productService.save(product);
		return NONE;
	}
}
