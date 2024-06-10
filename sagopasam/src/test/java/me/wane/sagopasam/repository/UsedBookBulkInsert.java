package me.wane.sagopasam.repository;

import lombok.extern.slf4j.Slf4j;
import me.wane.sagopasam.entity.BookImage;
import me.wane.sagopasam.entity.UsedBook;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.ofType;

@Slf4j
@SpringBootTest
public class UsedBookBulkInsert {

    @Autowired
    UsedBookRepository usedBookRepository;

    @Autowired
    BookImageRepository bookImageRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void bulkInsert() {
//        jdbcTemplate.batchUpdate(
//                ""
//        )
    }

    @Test
    void jpaInsert() {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .excludeField(field -> field.getName().equals("id"))
                .dateRange(LocalDate.of(2021, 1, 1), LocalDate.now())
                .randomize(Integer.class, () -> (int) (Math.random() * 1_000_000));


        EasyRandom easyRandom = new EasyRandom(parameters);

        List<UsedBook> list = IntStream.range(0, 50000)
                .parallel() // 병렬로 실행시켜서 객체 생성 속도 증가
                .mapToObj(i -> {
                    UsedBook usedBook = easyRandom.nextObject(UsedBook.class);
//                    usedBook.setBookImageList(List.of(
//                            new BookImage("https://www.naver.com",usedBook),
//                            new BookImage("https://www.daum.net", usedBook),
//                            new BookImage("https://www.google.com", usedBook)
//                    ));
                    return usedBook;
                }).toList();


        usedBookRepository.saveAll(list);
    }

}
