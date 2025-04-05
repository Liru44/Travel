package com.travel.travel.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private String id;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String role;
}
