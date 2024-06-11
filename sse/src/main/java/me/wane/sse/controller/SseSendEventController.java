package me.wane.sse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.wane.sse.repository.SseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SseSendEventController {

    private final SseRepository sseRepository;

    @GetMapping("/sse/{id}/send")
    public ResponseEntity<Void> sendEvent(@PathVariable Long id, @RequestParam String message) {
        Optional<SseEmitter> optionalSseEmitter = sseRepository.findById(id);

        if (optionalSseEmitter.isPresent()) {
            SseEmitter sseEmitter = optionalSseEmitter.get();
            try {
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .id(String.valueOf(System.currentTimeMillis()))
                        .name("message")
                        .data(message)
                        .comment("comment")
                        .reconnectTime(10000L);

                sseEmitter.send(event);

                return ResponseEntity.ok().build();

            } catch (IOException e) {
//                sseEmitter.completeWithError(e);
                return ResponseEntity.badRequest().build();
            }
        }

        log.warn("해당 id에 해당하는 sseEmitter가 없습니다. id: {}", id);
        return ResponseEntity.badRequest().build();

    }
}
