package me.wane.sagopasam.repository;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.wane.sagopasam.dto.QUsedBookListResponse;
import me.wane.sagopasam.dto.UsedBookListResponse;
import me.wane.sagopasam.vo.BookStatus;
import me.wane.sagopasam.vo.College;
import me.wane.sagopasam.vo.Department;

import java.util.List;

import static me.wane.sagopasam.entity.QBookImage.bookImage;
import static me.wane.sagopasam.entity.QUsedBook.usedBook;

@RequiredArgsConstructor
public class UsedBookRepositoryImpl implements UsedBookRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UsedBookListResponse> getUsedBookListInCollegeAndDepartment(List<College> college, List<Department> department, boolean isOrderByTradeAvailableDatetime) {
        JPAQuery<UsedBookListResponse> query = jpaQueryFactory.select(
                        new QUsedBookListResponse(
                                usedBook.id,
                                usedBook.mainImageUrl,
                                usedBook.bookStatus,
                                usedBook.name,
                                usedBook.tradeAvailableDatetime,
                                usedBook.createdAt,
                                usedBook.price
                        )
                ).from(usedBook)
                .where(usedBook.college.in(college).or(usedBook.department.in(department)))
                .where(
                        usedBook.isDeleted.eq(false)
//                        ,usedBook.sellerMember.isWithdraw.eq(false)
                )
//                .orderBy(usedBook.tradeAvailableDatetime.asc());
                .orderBy(usedBook.id.desc());

//        return query.fetch();

        if (isOrderByTradeAvailableDatetime) {
            return query
//                    .where(usedBook.isDeleted.eq(false),
//                            usedBook.sellerMember.isWithdraw.eq(false)
//                    )
//                    .orderBy(
//                            new CaseBuilder()
//                                    .when(usedBook.bookStatus.eq(BookStatus.SALE)).then(1)
//                                    .when(usedBook.bookStatus.eq(BookStatus.TRADE)).then(2)
//                                    .when(usedBook.bookStatus.eq(BookStatus.SOLD)).then(3)
//                                    .otherwise(4)
//                                    .asc(),
//                            usedBook.tradeAvailableDatetime.desc()
//                    )
                    .offset(100)
                    .limit(100)
                    .fetch();
        } else {

            return query
//                    .where(usedBook.isDeleted.eq(false),
//                            usedBook.sellerMember.isWithdraw.eq(false)
//                    )
//                    .orderBy(
//                            new CaseBuilder()
//                                    .when(usedBook.bookStatus.eq(BookStatus.SALE)).then(1)
//                                    .when(usedBook.bookStatus.eq(BookStatus.TRADE)).then(2)
//                                    .when(usedBook.bookStatus.eq(BookStatus.SOLD)).then(3)
//                                    .otherwise(4)
//                                    .asc(),
//                            usedBook.id.desc()
//                    )
                    .offset(100)
                    .limit(100)
                    .fetch();
        }
    }
}
