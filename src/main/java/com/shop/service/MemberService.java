package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor // 생성자 자동으로 만들어줌
//UserDetailService : db에서 회원 정보를 가져오는 기능을 수행
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    //    회원가입할때 중복회원인지 확인, 중복체크는 서비스에서 함=>하고 db 에 저장
    public Member saveMember(Member member){
        validateDuplicateMember(member); //중복회원인지 확인, 가입안되있으면 밑에 save
        return memberRepository.save(member);
    }
    private void validateDuplicateMember(Member member){
//        이메일로 중복회원 찾기
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원 입니다.");
        }
    }
    @Override //회원정보를 조회하여 사용자의 정보와 권한을 갖는 UserDetail 인터페이스를 반환
    //UserDetails : 스프링 시큐리티에서 회원 정보를 담기 위해서 사용하는 인터페이스
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if(member == null) {
            throw new UsernameNotFoundException(email);
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
