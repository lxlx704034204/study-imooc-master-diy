package com.myimooc.designpattern.c5observer.weathercondition;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe 天气目标抽象类
 * @author zc
 * @version 1.0 2017-08-29
 */
public abstract class WeatherSubject {
	
	/** 用来保存注册的观察者对象 */
	protected List<Observer> observers = new ArrayList<Observer>();
	
	/**
	 * 增加观察者
	 */
	public void attach(Observer observer){
		observers.add(observer);
	}
	
	/**
	 * 删除观察者
	 */
	public void detach(Observer observer){
		observers.remove(observer);
	}
	
	/**
	 * 区别通知观察者-由子类实现
	 */
	protected abstract void notifyObservers();
	
}
