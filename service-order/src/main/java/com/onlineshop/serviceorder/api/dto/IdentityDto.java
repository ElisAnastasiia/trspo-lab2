package com.onlineshop.serviceorder.api.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdentityDto {
    private long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}

