package me.wane.sse.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseEmitterRepository implements SseRepository {

    private static final Map<Long, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    @Override
    public SseEmitter createAndSendConnectEvent(Long id) {
        SseEmitter sseEmitter = new SseEmitter(10 * 60 * 1000L);
        setSseEmitter(id, sseEmitter);

        sseEmitterMap.put(id, sseEmitter);

        SseEmitter.SseEventBuilder event = buildConnectEvent(id);
        try {
            sseEmitter.send(event);
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
            log.info("error sseEmitter : {}", id);
        }

        return sseEmitter;
    }

    @Override
    public Optional<SseEmitter> findById(Long id) {
        return sseEmitterMap.get(id) == null ? Optional.empty() : Optional.of(sseEmitterMap.get(id));
    }

    @Override
    public void deleteById(Long id) {
        SseEmitter remove = sseEmitterMap.remove(id);

        log.info("deleteById sseEmitter: {}", id);
        remove.complete();

    }

    @Override
    public void deleteAll() {
        sseEmitterMap.forEach((id, sseEmitter) -> {
            log.info("deleteAll sseEmitter: {}", id);
            sseEmitter.complete();
        });
    }

    private SseEmitter.SseEventBuilder buildConnectEvent(Long id) {
        SseEmitter.SseEventBuilder event = SseEmitter.event()
                .id(id.toString())
                .name("connect")
                .data(Map.of("data", "connected"), MediaType.APPLICATION_JSON);
        return event;
    }

    private void setSseEmitter(Long id, SseEmitter sseEmitter) {
        sseEmitter.onTimeout(() -> {
            log.info("timeout sseEmitter : {}", id);
            sseEmitterMap.remove(id);
        });

        sseEmitter.onCompletion(() -> {
            log.info("complete sseEmitter : {}", id);
            sseEmitterMap.remove(id);
        });

        sseEmitter.onError((ex) -> {
            log.warn("sseEmitter error id:{} : {} ", id, ex.getMessage());
            sseEmitter.complete();
        });
    }

}
