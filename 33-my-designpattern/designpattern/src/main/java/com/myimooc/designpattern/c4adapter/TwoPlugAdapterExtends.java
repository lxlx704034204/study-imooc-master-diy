package com.myimooc.designpattern.c4adapter;

/**
 * @describe 二相转三相的插座适配器-继承方式
 * @author zc
 * @version 1.0 2017-08-29
 */
public class TwoPlugAdapterExtends extends GbTwoPlug implements ThreePlug {
	
	@Override
	public void powerWithThree() {
		System.out.println("通过转换-继承方式");
		this.powerWithTwo();
	}
}
