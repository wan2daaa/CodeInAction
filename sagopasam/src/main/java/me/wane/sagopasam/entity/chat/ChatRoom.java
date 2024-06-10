package me.wane.sagopasam.entity.chat;

import jakarta.persistence.*;
import lombok.*;
import me.wane.sagopasam.entity.Member;
import me.wane.sagopasam.entity.UsedBook;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@AllArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue
    private Long chatRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member seller;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuyerChat> buyerChats = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SellerChat> sellerChats = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private UsedBook usedBook;

    @Setter
    @Builder.Default
    private int interactStep = 0; // ContentType 에 따른 1 ~ 5 단계

//    @OneToOne(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Locker locker;

    public static ChatRoom buildChatRoom(Member buyer , Member seller , UsedBook usedBook) {
        return ChatRoom.builder().buyer(buyer).seller(seller).usedBook(usedBook).build();
    }

    public void addBuyerChat(BuyerChat chat) {
        if (this.getBuyerChats() == null) {
            this.buyerChats = new ArrayList<>();
            this.getBuyerChats().add(chat);
        }else {
            this.getBuyerChats().add(chat);
        }
    }

    public void addSellerChat(SellerChat chat) {
        if (this.getSellerChats() == null) {
            this.sellerChats = new ArrayList<>();
            this.getSellerChats().add(chat);
        }else {
            this.getSellerChats().add(chat);
        }
    }
}
