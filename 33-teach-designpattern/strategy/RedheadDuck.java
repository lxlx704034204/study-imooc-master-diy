package com.imooc.pattern.strategy;

import com.imooc.pattern.strategy.impl.FlyWithWin;



public class RedheadDuck extends Duck {

	public RedheadDuck(){
		super();
		super.setFlyingStragety(new FlyWithWin());
	}
	
	@Override
	public void display() {
		System.out.println("�ҵ�ͷ�Ǻ�ɫ��");
	}

}
