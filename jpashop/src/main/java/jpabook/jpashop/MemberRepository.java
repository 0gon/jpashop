package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Repository;


@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Integer save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Integer id){
        return em.find(Member.class, id);
    }
}
