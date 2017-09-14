package com.myimooc.designpattern.c3proxy.car;

/**
 * @describe 汽车行驶时间的代理
 * @author zc
 * @version 1.0 2017-08-28
 */
public class CarTimeProxy implements Moveable {
	
	// 因为代理类和被代理类都是实现相同的接口，所以构造方法传递的对象也可以是Moveable对象
	public CarTimeProxy(Moveable m) {
		super();
		this.m = m;
	}

	private Moveable m;
	
	@Override
	public void move() {
		
		long starttime = System.currentTimeMillis();
		System.out.println("汽车开始行驶...");
		
		m.move();
		
		long endtime = System.currentTimeMillis();
		System.out.println("汽车结束行驶...汽车行驶时间："+(endtime - starttime) + "毫秒");
		
	}

}
