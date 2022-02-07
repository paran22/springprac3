package com.sparta.week03project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String name;
    private String nickName;
    private boolean admin = false;
    private String adminToken = "";
}