package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
//@AllArgsConstructor
// final 또는 @NonNull 어노테이션 붙은 필드만 대상으로 하는 생성자 자동 생성(bean 자동 주입)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // setter injection
    // 이렇게 하면 테스트 환경에서 내가 원하는 멤버레포를 직접 주알할 수 있다
    // 애플리케이션 로딩 시점에 이미 필요한 레포를 업로드 후에 변경할 일이 없으므로 요즘 잘 안씀
//    @Autowired
//    private void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    // 요즘 권장하는줌 방식
    // 생성자 인젝션
    // 이 방식은 스프링에서 기본으로 지원해주기때문에 autowired 안해도됨

//    lombok allArg이 밑에꺼 생성해주는 어노테이션
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDupicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupicateMember(Member member) {
        //exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }
}
