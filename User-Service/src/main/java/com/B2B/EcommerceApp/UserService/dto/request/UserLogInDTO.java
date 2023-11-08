package com.B2B.EcommerceApp.UserService.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class UserLogInDTO {
    private String userEmail;
    private String userPassword;
}
