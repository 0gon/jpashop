package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

// embeddable memeber 테이블에 칼럼으로 city, street, zipcode가 있는데
// 테이블은 하나로 다 적는데
// 객체로 봤을 때 다 넣기보단 분할하는게 좋을 거 같아서 나누고싶을때 사용
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // jpa 스펙 상 기본생성자 필요해서 만듬
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
