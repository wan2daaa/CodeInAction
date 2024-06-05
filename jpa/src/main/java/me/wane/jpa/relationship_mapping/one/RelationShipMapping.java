package me.wane.jpa.relationship_mapping.one;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RelationShipMapping {

    private final EntityManagerFactory emf;

    public void save() {

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //insert into team (name) values (?)
        Team team1 = new Team( "team1");
        em.persist(team1);

        //insert into member (team_id,username) values (?,?)
        Member member1 = new Member("member1");
        member1.setTeam(team1); // 연관관계 설정
        em.persist(member1);

        //insert into member (team_id,username) values (?,?)
        Member member2 = new Member("member2");
        member2.setTeam(team1); // 연관관계 설정
        em.persist(member2);

        tx.commit();
    }

    public void find() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = em.find(Member.class, 1L);
        Team team = member.getTeam(); // 객체 그래프 탐색

        log.info("team = {}", team.getName());
        tx.commit();
    }

    public void jpqlFind() {
        EntityManager em = emf.createEntityManager();

        String jpql = "select m from MemberTwoWay m join m.team t where t.name=:teamName";

        em.createQuery(jpql, Member.class)
                .setParameter("teamName", "team1")
                .getResultList()
                .forEach(member -> log.info("member = {}", member.getUsername()));
    }

    public void update() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team2 = new Team("team2");
        em.persist(team2);

        Member member = em.find(Member.class, 1L);
        member.setTeam(team2); // 연관관계 수정

        tx.commit();
    }

    public void delete() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = em.find(Team.class, 1L);

        Member member1 = em.find(Member.class, 1L);
        member1.setTeam(null);

        Member member2 = em.find(Member.class, 2L);
        member2.setTeam(null);

        em.remove(team);

        tx.commit();
    }
}
