package me.wane.sse.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class SseTimeoutController {

    @GetMapping(value = "/sse-timeout", produces = MediaType.TEXT_EVENT_STREAM_VALUE )
    public ResponseEntity<SseEmitter> sseTimeout() {
        SseEmitter sseEmitter = new SseEmitter(5 * 1000L);

        try {
            sseEmitter.send("connect", MediaType.APPLICATION_JSON);

            sseEmitter.complete();
//            sseEmitter.send();

//            SseEmitter.SseEventBuilder event = SseEmitter.event()
//                    .id("1")
//                    .name("connect")
//                    .data(Map.of("data", "connected"), MediaType.APPLICATION_JSON)
//                    .reconnectTime(5 * 1000L);
//            sseEmitter.send(event);
//            Set<ResponseBodyEmitter.DataWithMediaType> dataSet = new HashSet<>();
//
//            dataSet.add(new ResponseBodyEmitter.DataWithMediaType(Map.of("hello", "world"), MediaType.APPLICATION_JSON));
//            dataSet.add(new ResponseBodyEmitter.DataWithMediaType(Map.of("data", "connected"), MediaType.APPLICATION_JSON));
//
//            sseEmitter.send(dataSet);
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }

        return ResponseEntity.ok(sseEmitter);
    }
}
