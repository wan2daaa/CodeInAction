package me.wane.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducer {
    private final KafkaTemplate<String, String> template;
    private final ObjectMapper objectMapper;

    public void sendMessage(String topic, String key, Object value) {
        try {
            String stringValue = objectMapper.writeValueAsString(value);

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, stringValue);
            try (Producer<String, String> producer = template.getProducerFactory()
                    .createProducer()) {
                producer.send(record, (metadata, exception) -> {
                    if (exception == null) {
                        log.info("Message sent successfully. record.value() = {} , Offset: {} , ", record.value(), metadata.offset());
                    } else {
                        log.error("failed to SendMessage to Kafka value = {} ", value, exception);
                    }
                });
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
