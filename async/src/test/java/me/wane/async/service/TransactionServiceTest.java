package me.wane.async.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    void asyncTest() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        transactionService.transactionWithAsyncMethod();
        stopWatch.stop();

        System.out.println("Elapsed Time: " + stopWatch.getTotalTimeSeconds());
    }

    @Test
    void syncTest() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        transactionService.transactionWithSyncMethod();
        stopWatch.stop();

        System.out.println("Elapsed Time: " + stopWatch.getTotalTimeSeconds());
    }


}