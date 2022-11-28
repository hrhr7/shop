package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor //롬복 사용해서 생성자 자동 생성
public class MemberController {
//    final 붙이면 생성자 안만들어줘도 됨
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
//    model 매개변수 받아서 값을 넣어줌
    public String memberForm(Model model){
//        일단 빈 객체 생성해서 타임리프에 넘겨줌
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }
    @PostMapping("/new")
    public String memberForm(MemberFormDto memberFromDto){
        Member member = Member.createMember(memberFromDto, passwordEncoder);
//        서비스 로직 부름(db에 저장하기 위해)
        memberService.saveMember(member);
//        로그인페이지는 상위 페이지라 루트path로 잡아줘야함
//        redirect 경로 바뀌는 코드 (라우터랑 비슷)
        return "redirect:/";

    }
    @GetMapping("/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }


















}
