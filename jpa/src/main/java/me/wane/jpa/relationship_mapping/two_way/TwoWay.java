package me.wane.jpa.relationship_mapping.two_way;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TwoWay {

    private final EntityManagerFactory emf;

    public void save() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        TeamTwoWay team1 = new TeamTwoWay("team1");
        em.persist(team1);

        MemberTwoWay member1 = new MemberTwoWay("member1");
        member1.setTeam(team1);
        em.persist(member1);


        MemberTwoWay member2 = new MemberTwoWay("member2");
        member2.setTeam(team1);
        em.persist(member2);

        tx.commit();
    }

    public void find() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TeamTwoWay team = em.find(TeamTwoWay.class, 1L);
        List<MemberTwoWay> members = team.getMembers();

        for (MemberTwoWay member : members) {
            System.out.println("member = " + member.getUsername());
        }

        tx.commit();
    }
}
