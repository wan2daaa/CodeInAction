package me.wane.sagopasam.repository;

import me.wane.sagopasam.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookImageRepository extends JpaRepository<BookImage, Long> {
}