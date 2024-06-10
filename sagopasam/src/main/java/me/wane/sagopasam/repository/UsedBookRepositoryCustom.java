package me.wane.sagopasam.repository;

import me.wane.sagopasam.dto.UsedBookListResponse;
import me.wane.sagopasam.vo.College;
import me.wane.sagopasam.vo.Department;

import java.util.List;

public interface UsedBookRepositoryCustom {
    List<UsedBookListResponse> getUsedBookListInCollegeAndDepartment(List<College> college, List<Department> department, boolean isOrderByTradeAvailableDatetime);

}
