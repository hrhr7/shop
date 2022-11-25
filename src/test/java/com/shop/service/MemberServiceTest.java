package com.shop.service;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //이게 있어야 롤백됨
@Rollback(value = false) // 롤백 false 해놔야 데이터 들어감
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;
    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("jsk@naver.com");
        memberFormDto.setName("곰돌이");
        memberFormDto.setAddress("서울시 강남구");
        memberFormDto.setPassword("jsk2024");
        return Member.createMember(memberFormDto,passwordEncoder);
    }
    @Test
    @DisplayName("회원가입테스트")
    public void saveMemberTest(){
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);
//        두개 데이터 가져와서 비교
        assertEquals(member.getEmail(),savedMember.getEmail());
        assertEquals(member.getName(),savedMember.getName());
        assertEquals(member.getAddress(),savedMember.getAddress());
        assertEquals(member.getPassword(),savedMember.getPassword());
        assertEquals(member.getRole(),savedMember.getRole());
    }
}