package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor // 생성자 자동으로 만들어줌
public class MemberService {
    private final MemberRepository memberRepository;

    //    회원가입할때 중복회원인지 확인, 중복체크는 서비스에서 함=>하고 db 에 저장
    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }
    private void validateDuplicateMember(Member member){
//        이메일로 중복회원 찾기
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원 입니다.");
        }
    }
}
