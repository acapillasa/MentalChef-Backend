package com.mentalchef.demo.dto.userdtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserGetDto {
    private String username;
    private String email;
    private String role;
}