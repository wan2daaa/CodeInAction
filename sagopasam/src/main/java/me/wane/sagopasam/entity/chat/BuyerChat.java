package me.wane.sagopasam.entity.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import me.wane.sagopasam.dto.ChatMessageResponse;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class BuyerChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @ColumnDefault("false")
    @Builder.Default
    private Boolean isRead = false;

    @Column(nullable = false)
    private String message;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    public static BuyerChat buildBuyerChat(String message, ContentType contentType) {
        return BuyerChat.builder()
                .message(message)
                .contentType(contentType)
                .build();
    }

    public static BuyerChat buildBuyerChat(String message, ContentType contentType, ChatRoom chatRoom) {
        return BuyerChat.builder()
                .message(message)
                .contentType(contentType)
                .chatRoom(chatRoom)
                .build();
    }

    public ChatMessageResponse toChatMessageResponse() {
        return ChatMessageResponse.of(
                chatRoom.getChatRoomId(),
                message,
                contentType,
                createdAt
        );
    }

    public void updateIsReadTrue() {
        this.isRead = true;
    }
}
