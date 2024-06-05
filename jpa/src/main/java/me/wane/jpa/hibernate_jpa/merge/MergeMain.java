package me.wane.jpa.hibernate_jpa.merge;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.wane.jpa.hibernate_jpa.entity.Member;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MergeMain {

    private final EntityManagerFactory emf;

/**
    영속성 컨텍스트 1 시작
    insert into member(age, name, id) values (?,?,?)
    영속성 컨텍스트 1종료, ->member 엔티티는 준영속 상태가 된다 .
    준영속 상태에서 member 엔티티의 username 변경
    영속성 컨텍스트 2시작
    select m1_0.id,m1_0.age, m1_0.name from member m1_0 where m1_0.id=?

     update member set age =?,name=? where id =?
    준영속 상태 member = Member(id=1, username=wane2, age=0)
    영속 상태 mergedMember = Member(id=1, username=wane2, age=0)
    entity manager contains member :false
    entity manager contains mergedMember :true
    영속성 컨텍스트 2종료
 */
    public void mergeMain() {
        Member member = createMember(1L, "wane");

        log.info("준영속 상태에서 member 엔티티의 username 변경");
        member.setUsername("wane2"); // 준영속 상태

        mergeMember(member);

    }

    private Member createMember(Long id, String username) {
        log.info("영속성 컨텍스트 1 시작");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);

        em.persist(member);
        tx.commit();

        em.close();
        log.info("영속성 컨텍스트 1 종료, -> member 엔티티는 준영속 상태가 된다.");

        return member;
    }

    private void mergeMember(Member member) {
        log.info("영속성 컨텍스트 2 시작 ");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Member mergedMember = em.merge(member);
        tx.commit();

        log.info("준영속 상태 member = {}", member);

        log.info("영속 상태 mergedMember = {}", mergedMember);

        log.info("entity manager contains member : {}", em.contains(member));
        log.info("entity manager contains mergedMember : {}", em.contains(mergedMember));

        em.close();
        log.info("영속성 컨텍스트 2 종료");
    }
}
