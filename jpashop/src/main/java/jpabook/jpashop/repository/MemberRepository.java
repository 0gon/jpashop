package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // auto는 스프링dataJPA에서는 주입이 되지만 기본적으로 @Persisment로만 주입이 된다
    // @RequiredArgs로 생성자에서 의존성을 주입한다.? jpa라서 주입이 가능한가?
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
