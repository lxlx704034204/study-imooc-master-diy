 package com.imooc.pattern.template;

/*
 * �������࣬�ṩ�˿����Ʊ��ľ���ʵ��
 */
public class Coffee extends RefreshBeverage {

	@Override
	protected void brew() {
		System.out.println("�÷�ˮ���ݿ���");

	}

	@Override
	protected void addCondiments() {
		System.out.println("�����Ǻ�ţ��");
	}

}
