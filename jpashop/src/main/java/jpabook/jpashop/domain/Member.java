package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    //mappedBy 나는 주인이 아니야 order 안의 memeber란 이름을 가진 애가 나의 주인이야
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}

