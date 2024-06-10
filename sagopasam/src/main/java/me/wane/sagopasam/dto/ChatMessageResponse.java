package me.wane.sagopasam.dto;

import com.querydsl.core.annotations.QueryProjection;
import me.wane.sagopasam.entity.chat.ContentType;

import java.time.LocalDateTime;

public record ChatMessageResponse(
        long chatRoomId,
        String message,
        ContentType contentType,
        LocalDateTime createdAt
) {

    public static ChatMessageResponse of(
            long chatRoomId,
            String message,
            ContentType contentType,
            LocalDateTime createdAt
    ) {
        return new ChatMessageResponse(
                chatRoomId,
                message,
                contentType,
                createdAt
        );
    }

    @QueryProjection
    public ChatMessageResponse(long chatRoomId, String message, ContentType contentType, LocalDateTime createdAt) {
        this.chatRoomId = chatRoomId;
        this.message = message;
        this.contentType = contentType;
        this.createdAt = createdAt;
    }
}
