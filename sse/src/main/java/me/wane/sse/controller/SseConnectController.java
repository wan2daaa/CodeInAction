package me.wane.sse.controller;

import lombok.RequiredArgsConstructor;
import me.wane.sse.repository.SseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequiredArgsConstructor
@RestController
public class SseConnectController {

    private final SseRepository sseRepository;

    @GetMapping(value = "/sse/connect/{id}")
    public ResponseEntity<SseEmitter> connect(@PathVariable Long id) {

        SseEmitter sseEmitter = sseRepository.createAndSendConnectEvent(id);

        return ResponseEntity.ok(sseEmitter);
    }
}
