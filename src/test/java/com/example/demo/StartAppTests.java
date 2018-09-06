package com.example.demo;

import com.example.demo.test.TestAutoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestStartApp.class)
public class StartAppTests {

    @Autowired
    private TestAutoConfig testAutoConfig;
    @Test
    public void contextLoads() {
    }

}
