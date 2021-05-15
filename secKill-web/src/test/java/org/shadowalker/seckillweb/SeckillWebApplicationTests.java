package org.shadowalker.seckillweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;

@SpringBootTest
class SeckillWebApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(SeckillWebApplicationTests.class);
    private static ExecutorService executorServicePool;
    private static String url = "http://127.0.0.1:8080/createOptimisticLimitOrder/1";

}
