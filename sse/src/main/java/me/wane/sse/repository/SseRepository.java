package me.wane.sse.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Optional;

public interface SseRepository {

    SseEmitter createAndSendConnectEvent(Long id);

    Optional<SseEmitter> findById(Long id);

    void deleteById(Long id);

    void deleteAll();
}
