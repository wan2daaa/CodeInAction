package me.wane.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducerScheduler {

    private final KafkaProducer kafkaProducer;

    private static Long VALUE = 1L;

    @Scheduled(fixedRate = 1000)
    public void schedule() {
        kafkaProducer.sendMessage("test", "key", VALUE);
        VALUE++;
    }

}
