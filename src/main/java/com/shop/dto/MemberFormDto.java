package com.shop.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

// 회원 가입화면으로 부터 넘어오는 가입 정보를 담을 dto 객체
@Getter
@Setter
public class MemberFormDto {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일 형식으로 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "주소는 필수 입력값입니다.")
    private String address;
}