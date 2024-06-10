package me.wane.sagopasam.repository;

import me.wane.sagopasam.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}