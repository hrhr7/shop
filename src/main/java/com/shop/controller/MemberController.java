package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor //롬복 사용해서 생성자 자동 생성
public class MemberController {
//    final 붙이면 생성자 안만들어줘도 됨
    private final MemberService memberService;
    @GetMapping("/new")
//    model 매개변수 받아서 값을 넣어줌
    public String memberForm(Model model){
//        일단 빈 객체 생성해서 타임리프에 넘겨줌
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "member/memberForm";

    }
}
