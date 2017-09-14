package com.myimooc.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

/**
 * 需定时调度的业务逻辑类
 * Created by ChangComputer on 2017/5/24.
 */
// 继承 TimerTask 类
public class MyTimerTask extends TimerTask{
    private String name;

    // 计数器，没执行一次加一
    private Integer count = 0;
    public MyTimerTask(String name){
        this.name = name;
    }

    // 重写 run 方法
    @Override
    public void run() {
        if (count < 3) {
            // 以yyyy-MM-dd HH:mm:ss的格式打印当前执行时间
            // 如2016-11-11 00:00:00
            Calendar calendar = Calendar.getInstance();
            // 定义日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Current exec time is : " + simpleDateFormat.format(calendar.getTime()));

            // 打印当前 name 的内容
            System.out.println("Current exec name is : " + name);

            count++;
        }else{
            // 取消任务执行
            cancel();
            System.out.println("Task cancel!");
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
