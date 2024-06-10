package me.wane.sagopasam.repository.chat;

import me.wane.sagopasam.dto.ChatListResponse;
import me.wane.sagopasam.dto.ChatMessageResponse;
import me.wane.sagopasam.entity.Member;
import me.wane.sagopasam.entity.UsedBook;
import me.wane.sagopasam.entity.chat.BuyerChat;
import me.wane.sagopasam.entity.chat.ChatRoom;
import me.wane.sagopasam.entity.chat.SellerChat;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepositoryCustom {

    List<SellerChat> getSellerChatting(long chatRoomId);

    List<ChatMessageResponse> getNotReadSellerChattingData(long chatRoomId);

    List<BuyerChat> getBuyerChatting(long chatRoomId);

    List<ChatMessageResponse> getNotReadBuyerChattingData(long chatRoomId);

    Member getSeller(long chatRoomId);

    long modifySellerChattingToRead(long chatRoomId);

    long modifyBuyerChattingToRead(long chatRoomId);

    Optional<ChatRoom> findChatRoomAndBookById(long chatRoomId);

    Optional<ChatRoom> findBookBySellerAndBuyerAndBook(Member seller, Member buyer,
                                                       UsedBook usedBook);

    List<ChatListResponse> findByChatroomListWithNickname(String nickname);

    List<ChatMessageResponse> findChatMessageByChatroomIdWithBuyerNickname(long chatRoomId,
                                                                           String nickname);

    List<ChatMessageResponse> findChatMessageByChatroomIdWithSellerNickname(long chatRoomId,
                                                                            String nickname);

    void updateSellerChattingToRead(long chatRoomId);

    void updateBuyerChattingToRead(long chatRoomId);

    Member getBuyer(long chatRoomId);
}

