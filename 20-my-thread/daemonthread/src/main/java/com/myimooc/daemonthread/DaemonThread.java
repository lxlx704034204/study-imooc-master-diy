package com.myimooc.daemonthread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 守护线程。使用 Runnable 创建线程
 * @author ZhangCheng on 2017-07-08
 *
 */
public class DaemonThread implements Runnable {

	@Override
	public void run() {
		System.out.println("进入守护线程" + Thread.currentThread().getName());
		
		try {
			writeToFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("退出守护线程" + Thread.currentThread().getName());
	}

	private void writeToFile() throws Exception{
		
		File fileName = new File("d:" + File.separator + "daemon.txt");
		// 向文件中追加数据
		OutputStream os = new FileOutputStream(fileName,true);
		int count = 0;
		while(count < 999){
			os.write(("\r\nword" + count).getBytes());
			System.out.println("守护线程" + Thread.currentThread().getName() 
				+ "向文件中写入了word" + count);
			count++;
			// 线程休眠1秒
			Thread.sleep(1000);
		}
		os.close();
	}
}
