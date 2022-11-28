package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultHandlers;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc //MockMvc 테스트를 위해 어노테이션 선언
@Transactional
class MemberControllerTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    PasswordEncoder passwordEncoder;
//   로그인 테스트 진행 이전에 회원 등록하는 메소드 만들기
    public Member createMember(String email, String password){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail(email);
        memberFormDto.setName("곰돌이");
        memberFormDto.setAddress("서울시 강남구 역삼동");
        memberFormDto.setPassword(password);
        Member member = Member.createMember(memberFormDto,passwordEncoder);
        return memberService.saveMember(member);
    }
    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception{
        String email = "test@email.com";
        String password = "12345";
        this.createMember(email, password);
        mockMvc.perform(formLogin().passwordParam("email")
                .loginProcessingUrl("/member/login") //회원가입 메소드 실행 후 가입 된 정보로 로그인 되는지
                .user(email).password(password))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated()); //로그인 성공해서 인증이 되면 통과, 에러나서 un으로
    }
}