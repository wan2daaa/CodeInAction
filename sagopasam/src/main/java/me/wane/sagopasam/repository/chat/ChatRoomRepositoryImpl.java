package me.wane.sagopasam.repository.chat;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.wane.sagopasam.dto.ChatListResponse;
import me.wane.sagopasam.dto.ChatMessageResponse;
import me.wane.sagopasam.dto.QChatListResponse;
import me.wane.sagopasam.dto.QChatMessageResponse;
import me.wane.sagopasam.entity.Member;
import me.wane.sagopasam.entity.QMember;
import me.wane.sagopasam.entity.UsedBook;
import me.wane.sagopasam.entity.chat.BuyerChat;
import me.wane.sagopasam.entity.chat.ChatRoom;
import me.wane.sagopasam.entity.chat.SellerChat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static me.wane.sagopasam.entity.QBookImage.bookImage;
import static me.wane.sagopasam.entity.QMember.member;
import static me.wane.sagopasam.entity.QUsedBook.usedBook;
import static me.wane.sagopasam.entity.chat.QBuyerChat.buyerChat;
import static me.wane.sagopasam.entity.chat.QChatRoom.chatRoom;
import static me.wane.sagopasam.entity.chat.QSellerChat.sellerChat;

@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SellerChat> getSellerChatting(long chatRoomId) {
        return jpaQueryFactory.select(sellerChat)
                .from(chatRoom)
                .innerJoin(chatRoom.sellerChats, sellerChat)
                .where(chatRoom.chatRoomId.eq(chatRoomId))
                .fetch();
    }

    @Override
    public List<ChatMessageResponse> getNotReadSellerChattingData(long chatRoomId) {
        return jpaQueryFactory.select(
                        new QChatMessageResponse(chatRoom.chatRoomId, sellerChat.message, sellerChat.contentType,
                                sellerChat.createdAt))
                .from(sellerChat)
                .innerJoin(chatRoom).on(sellerChat.chatRoom.eq(chatRoom))
                .where(
                        sellerChat.isRead.eq(false),
                        sellerChat.chatRoom.chatRoomId.eq(chatRoomId)
                )
                .fetch();
    }

    @Override
    public List<BuyerChat> getBuyerChatting(long chatRoomId) {
        return jpaQueryFactory.select(buyerChat)
                .from(chatRoom)
                .innerJoin(chatRoom.buyerChats, buyerChat)
                .where(chatRoom.chatRoomId.eq(chatRoomId))
                .fetch();
    }

    @Override
    public List<ChatMessageResponse> getNotReadBuyerChattingData(long chatRoomId) {
        return jpaQueryFactory.select(
                        new QChatMessageResponse(chatRoom.chatRoomId, buyerChat.message, buyerChat.contentType,
                                buyerChat.createdAt))
                .from(buyerChat)
                .innerJoin(chatRoom).on(buyerChat.chatRoom.eq(chatRoom))
                .where(
                        buyerChat.isRead.eq(false),
                        buyerChat.chatRoom.chatRoomId.eq(chatRoomId)
                )
                .fetch();
    }

    @Override
    public Member getSeller(long chatRoomId) {
        return jpaQueryFactory.select(member)
                .from(chatRoom)
                .innerJoin(chatRoom.seller, member)
                .where(chatRoom.chatRoomId.eq(chatRoomId))
                .fetchOne();
    }

    @Override
    public long modifySellerChattingToRead(long chatRoomId) {
        return jpaQueryFactory.update(sellerChat)
                .set(sellerChat.isRead, true)
                .where(sellerChat.chatRoom.chatRoomId.eq(chatRoomId))
                .execute();
    }

    @Override
    public long modifyBuyerChattingToRead(long chatRoomId) {
        return jpaQueryFactory.update(buyerChat)
                .set(buyerChat.isRead, true)
                .where(buyerChat.chatRoom.chatRoomId.eq(chatRoomId))
                .execute();
    }

    @Override
    public Optional<ChatRoom> findChatRoomAndBookById(long chatRoomId) {
        ChatRoom result = jpaQueryFactory.select(chatRoom)
                .from(chatRoom)
                .innerJoin(chatRoom.usedBook).fetchJoin()
                .where(chatRoom.chatRoomId.eq(chatRoomId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<ChatRoom> findBookBySellerAndBuyerAndBook(Member seller, Member buyer,
                                                              UsedBook usedBookData) {
        QMember QSeller = new QMember("seller");

        ChatRoom result = jpaQueryFactory.select(chatRoom)
                .from(chatRoom)
                .innerJoin(chatRoom.usedBook, usedBook).on(usedBook.eq(usedBookData))
                .innerJoin(chatRoom.buyer, member).on(member.eq(buyer))
                .innerJoin(chatRoom.seller, QSeller).on(QSeller.eq(seller))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public List<ChatListResponse> findByChatroomListWithNickname(String nickname) {

        List<ChatListResponse> chatListResponseList = jpaQueryFactory
                .select(
                        new QChatListResponse(
                                chatRoom.chatRoomId,
                                usedBook.name,
                                sellerChat.message,
                                sellerChat.createdAt,
                                sellerChat.isRead,
                                JPAExpressions
                                        .select(bookImage.imageUrl)
                                        .from(bookImage)
                                        .where(bookImage.usedBook.id.eq(usedBook.id)
                                                .and(bookImage.id.eq(
                                                        JPAExpressions
                                                                .select(bookImage.id.min())
                                                                .from(bookImage)
                                                                .where(bookImage.usedBook.id.eq(usedBook.id))
                                                ))
                                        ))
                ).from(sellerChat)
                .innerJoin(chatRoom).on(sellerChat.chatRoom.eq(chatRoom))
                .innerJoin(usedBook).on(chatRoom.usedBook.eq(usedBook))
                .innerJoin(member).on(usedBook.sellerMember.eq(member))
                .where(
                        member.nickname.eq(nickname),
                        Expressions.list(sellerChat.chatRoom.chatRoomId, sellerChat.id).in(
                                JPAExpressions.select(sellerChat.chatRoom.chatRoomId, sellerChat.id.max())
                                        .from(sellerChat)
                                        .innerJoin(chatRoom).on(sellerChat.chatRoom.eq(chatRoom))
                                        .innerJoin(usedBook).on(chatRoom.usedBook.eq(usedBook))
                                        .innerJoin(member).on(usedBook.sellerMember.eq(member))
                                        .where(member.nickname.eq(nickname))
                                        .groupBy(sellerChat.chatRoom.chatRoomId)
                        ))
                .fetch();

        chatListResponseList.addAll(jpaQueryFactory
                .select(
                        new QChatListResponse(
                                chatRoom.chatRoomId,
                                usedBook.name,
                                buyerChat.message,
                                buyerChat.createdAt,
                                buyerChat.isRead,
                                JPAExpressions
                                        .select(bookImage.imageUrl)
                                        .from(bookImage)
                                        .where(bookImage.usedBook.id.eq(usedBook.id)
                                                .and(bookImage.id.eq(
                                                        JPAExpressions
                                                                .select(bookImage.id.min())
                                                                .from(bookImage)
                                                                .where(bookImage.usedBook.id.eq(usedBook.id))
                                                ))
                                        ))
                ).from(buyerChat)
                .innerJoin(chatRoom).on(buyerChat.chatRoom.eq(chatRoom))
                .innerJoin(usedBook).on(chatRoom.usedBook.eq(usedBook))
                .innerJoin(member).on(usedBook.buyerMember.eq(member))
                .where(
                        member.nickname.eq(nickname),
                        Expressions.list(buyerChat.chatRoom.chatRoomId, buyerChat.id).in(
                                JPAExpressions.select(buyerChat.chatRoom.chatRoomId, buyerChat.id.max())
                                        .from(buyerChat)
                                        .innerJoin(chatRoom).on(buyerChat.chatRoom.eq(chatRoom))
                                        .innerJoin(usedBook).on(chatRoom.usedBook.eq(usedBook))
                                        .innerJoin(member).on(usedBook.buyerMember.eq(member))
                                        .where(member.nickname.eq(nickname))
                                        .groupBy(buyerChat.chatRoom.chatRoomId)
                        )).fetch());

        chatListResponseList.sort(Comparator.comparing(ChatListResponse::createdAt,
                Comparator.nullsLast(Comparator.reverseOrder())));

        return chatListResponseList;
    }

    @Override
    public List<ChatMessageResponse> findChatMessageByChatroomIdWithBuyerNickname(long chatRoomId,
                                                                                  String nickname) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ChatMessageResponse.class,
                        buyerChat.chatRoom.chatRoomId,
                        buyerChat.message,
                        buyerChat.contentType,
                        buyerChat.createdAt
                )).from(buyerChat)
                .where(buyerChat.chatRoom.chatRoomId.eq(chatRoomId),
                        buyerChat.chatRoom.buyer.nickname.eq(nickname)
                )
                .orderBy(buyerChat.createdAt.asc())
                .fetch();
    }

    @Override
    public List<ChatMessageResponse> findChatMessageByChatroomIdWithSellerNickname(long chatRoomId,
                                                                                   String nickname) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ChatMessageResponse.class,
                        sellerChat.chatRoom.chatRoomId,
                        sellerChat.message,
                        sellerChat.contentType,
                        sellerChat.createdAt
                )).from(sellerChat)
                .where(sellerChat.chatRoom.chatRoomId.eq(chatRoomId),
                        sellerChat.chatRoom.seller.nickname.eq(nickname)
                ).orderBy(sellerChat.createdAt.asc())
                .fetch();
    }

    @Override
    public void updateSellerChattingToRead(long chatRoomId) {
        jpaQueryFactory.update(sellerChat)
                .set(sellerChat.isRead, true)
                .where(sellerChat.chatRoom.chatRoomId.eq(chatRoomId))
                .execute();
    }

    @Override
    public void updateBuyerChattingToRead(long chatRoomId) {
        jpaQueryFactory.update(buyerChat)
                .set(buyerChat.isRead, true)
                .where(buyerChat.chatRoom.chatRoomId.eq(chatRoomId))
                .execute();
    }

    @Override
    public Member getBuyer(long chatRoomId) {
        return jpaQueryFactory.select(member)
                .from(chatRoom)
                .innerJoin(chatRoom.buyer, member)
                .where(chatRoom.chatRoomId.eq(chatRoomId))
                .fetchOne();
    }

    private static JPQLQuery<Boolean> getLastSellerMessageIsRead() {
        return JPAExpressions.select(sellerChat.isRead)
                .from(sellerChat)
                .where(sellerChat.chatRoom.chatRoomId.eq(chatRoom.chatRoomId))
                .orderBy(sellerChat.createdAt.desc())
                .limit(1);
    }

    private static JPQLQuery<LocalDateTime> getLastSellerMessageCreatedAt() {
        return JPAExpressions.select(sellerChat.createdAt)
                .from(sellerChat)
                .where(sellerChat.chatRoom.chatRoomId.eq(chatRoom.chatRoomId))
                .orderBy(sellerChat.createdAt.desc())
                .limit(1);
    }

    private static JPQLQuery<String> getLastSellerMessage() {
        return JPAExpressions.select(sellerChat.message)
                .from(sellerChat)
                .where(sellerChat.chatRoom.chatRoomId.eq(chatRoom.chatRoomId))
                .orderBy(sellerChat.createdAt.desc())
                .limit(1);
    }

    private static JPQLQuery<String> getFirstUsedBookImageUrl() {
        return JPAExpressions.select(bookImage.imageUrl)
                .from(bookImage)
                .where(bookImage.usedBook.id.eq(usedBook.id))
                .orderBy(bookImage.id.asc())
                .limit(1);
    }

    private static JPQLQuery<Boolean> getLastBuyerMessageIsRead() {
        return JPAExpressions.select(buyerChat.isRead)
                .from(buyerChat)
                .where(buyerChat.chatRoom.chatRoomId.eq(chatRoom.chatRoomId))
                .orderBy(buyerChat.createdAt.desc())
                .limit(1);
    }

    private static JPQLQuery<LocalDateTime> getLastBuyerMessageCreatedAt() {
        return JPAExpressions.select(buyerChat.createdAt)
                .from(buyerChat)
                .where(buyerChat.chatRoom.chatRoomId.eq(chatRoom.chatRoomId))
                .orderBy(buyerChat.createdAt.desc())
                .limit(1);
    }

    private static JPQLQuery<String> getLastBuyerMessage() {
        return JPAExpressions.select(buyerChat.message)
                .from(buyerChat)
                .where(buyerChat.chatRoom.chatRoomId.eq(chatRoom.chatRoomId))
                .orderBy(buyerChat.createdAt.desc())
                .limit(1);
    }

}
