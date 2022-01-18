package com.onlineshop.serviceidentity.api.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Identity {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}