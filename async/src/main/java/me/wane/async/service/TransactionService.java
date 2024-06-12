package me.wane.async.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionService {

    private final AsyncSyncService asyncSyncService;

    @Transactional
    public void transactionWithAsyncMethod() {
        asyncSyncService.asyncMethod();

        log.info("asyncWithTransaction");
    }

    @Transactional
    public void transactionWithSyncMethod() {
        asyncSyncService.syncMethod();

        log.info("syncWithTransaction");
    }

}
