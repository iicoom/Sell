package com.icoom.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1() {
        // logger.debug("debug...");
        logger.info("info...");
        logger.error("error...");
    }
}

//public class LoggerTest {
//
//    @Test
//    public void test1() {
//        log.debug("debug...");
//        log.info("info...");
//        log.error("error...");
//    }
//}

// 每次都需要传当前的类名LoggerTest.class 比较麻烦 可以使用@Slf4j插件来简化 好像有问题