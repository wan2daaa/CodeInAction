package me.wane.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncSyncService {

    @Async
    public void asyncMethod() {
        log.info("asyncMethod start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("asyncMethod end");
    }

    public void syncMethod() {
        log.info("syncMethod start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("syncMethod end");
    }
}
