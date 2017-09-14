package com.byimooc.myssosamedomain;

import com.myimooc.sso.demo1.x.com.DemoOneController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoOneController.class)//base.java位于本分支下
public class ConsoleMainTests {

    @Test
    public void contextLoads() {
    }

}
