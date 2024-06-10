package me.wane.sagopasam.repository.chat;


import me.wane.sagopasam.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepositoryCustom {
    boolean existsByChatRoomIdAndBuyer_Nickname(long chatRoomId, String nickname);

    boolean existsByChatRoomIdAndSeller_Nickname(long chatRoomId, String nickname);

    boolean existsByBuyer_NicknameOrSeller_NicknameAndChatRoomId(String buyerNickname,
        String sellerNickname, Long chatRoomId);

    Optional<ChatRoom> findByChatRoomId(long chatRoomId);

    @Query("SELECT c FROM ChatRoom c " +
           "WHERE (c.seller.id = :sellerId OR c.buyer.id = :buyerId) " +
           "AND c.chatRoomId = :chatRoomId")
    Optional<ChatRoom> findBySellerOrBuyerAndChatRoomId(
        @Param("sellerId") Long sellerId,
        @Param("buyerId") Long buyerId,
        @Param("chatRoomId") Long chatRoomId
    );

    List<ChatRoom> findAllByInteractStep(int interactStep);
}
