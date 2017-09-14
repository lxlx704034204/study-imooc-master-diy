package com.myimooc.designpattern.c5observer.weather;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe 管理订阅者列表
 * @author zc
 * @version 1.0 2017-08-29
 */
public class WeatherSubject {
	
	/**
	 * 订阅者列表
	 */
	private List<Observer> observers = new ArrayList<Observer>();
	
	/**
	 * 把订阅天气的人增加到订阅者列表中
	 */
	public void attach(Observer observer){
		observers.add(observer);
	}
	
	/**
	 * 删除订阅的人
	 */
	public void detach(Observer observer){
		observers.remove(observer);
	}
	
	/**
	 * 通知所有已经订阅天气的人
	 */
	protected void notifyObserver() {
		observers.forEach(observer ->{
			observer.update(this);
		});
	}
}
