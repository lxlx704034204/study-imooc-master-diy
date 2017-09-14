package com.myimooc.helloquartz.one;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 编写 自定义任务
 * @author ZhangCheng on 2017-06-26
 *
 */
public class HelloJob implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 打印当前的执行时间，格式为2017-01-01 00:00:00
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current Exec Time Is : " + sf.format(date));
		
		// 编写具体的业务逻辑
		System.out.println("Hello World!");
	}
	
}
