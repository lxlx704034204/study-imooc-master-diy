package com.myimooc.designpattern.c5observer.weather;

/**
 * @describe 订阅天气-测试类
 * @author zc
 * @version 1.0 2017-08-29
 */
public class Client {
	
	public static void main(String[] args) {
		// 1.创建目标
		ConcreteWeatherSubject weather = new ConcreteWeatherSubject();
		
		// 2.创建观察者
		ConcreteObserver observerGiel = new ConcreteObserver();
		observerGiel.setObserverName("黄明的女朋友");
		observerGiel.setRemindThing("是我们的第一次约会，地点街心公园，不见不散哦");
		
		ConcreteObserver observerMum = new ConcreteObserver();
		observerMum.setObserverName("老妈");
		observerMum.setRemindThing("是一个购物的好日子，明天去天虹扫货");
		
		// 3.注册观察者
		weather.attach(observerGiel);
		weather.attach(observerMum);
		
		// 4.目标发布天气
		weather.setWeatherContent("明天 天气晴朗，蓝天白云，气温28℃");
	}
	
}
