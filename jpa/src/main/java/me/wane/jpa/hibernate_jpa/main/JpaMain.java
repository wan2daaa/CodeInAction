package me.wane.jpa.hibernate_jpa.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;
import me.wane.jpa.hibernate_jpa.entity.Member;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class JpaMain {

    private final EntityManagerFactory emf;

    public void main() {

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try (em) {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    private static void logic(EntityManager em) {
        Long id = 1L;
        // 비영속
        Member member = new Member();
        member.setId(id);
        member.setUsername("와닝");
        member.setAge(29);

        // 엔티티를 영속 , 1차 캐시에 저장
        em.persist(member);
        // insert into member (age,name,id) values (?,?,?)

        member.setAge(20);
        // update member set age=?,name=? where id=?

        //1차 캐시에서 조회
        Member findMember = em.find(Member.class, id);

        System.out.println("findMember = " + findMember);


        List<Member> members = em.createQuery("select m from MemberTwoWay m", Member.class)
                .getResultList();
        // /* select m from Member m */
        // select m1_0.id,m1_0.age,m1_0.name from member m1_0

        System.out.println("members.size() = " + members.size());

        em.remove(member);
        // delete from member where id=?
    }
}
