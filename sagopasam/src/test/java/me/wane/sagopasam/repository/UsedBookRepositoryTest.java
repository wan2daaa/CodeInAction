package me.wane.sagopasam.repository;

import lombok.extern.slf4j.Slf4j;
import me.wane.sagopasam.dto.UsedBookListResponse;
import me.wane.sagopasam.vo.College;
import me.wane.sagopasam.vo.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;


@Slf4j
@SpringBootTest
class UsedBookRepositoryTest {

    @Autowired
    private UsedBookRepository usedBookRepository;

    @Test
    void name() {
        List<College> colleges = List.of(College.GE, College.LAW);
        List<Department> departments = List.of(Department.GE, Department.DEPARTMENT_OF_LAW);

        Function<UsedBookListResponse, Integer> statusToNumber = response -> {
            switch (response.bookStatus()) {
                case SALE:
                    return 1;
                case TRADE:
                    return 2;
                case SOLD:
                    return 3;
                default:
                    return 4;
            }
        };


        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        List<UsedBookListResponse> responses = usedBookRepository.getUsedBookListInCollegeAndDepartment(colleges, departments, true);
        responses.sort(
                Comparator.comparing(statusToNumber)
        );
        stopWatch.stop();

        log.info("Elapsed Time: {}", stopWatch.getTotalTimeMillis());
    }
}