package me.wane.sagopasam.repository;

import me.wane.sagopasam.entity.UsedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedBookRepository extends JpaRepository<UsedBook, Long>, UsedBookRepositoryCustom {
}