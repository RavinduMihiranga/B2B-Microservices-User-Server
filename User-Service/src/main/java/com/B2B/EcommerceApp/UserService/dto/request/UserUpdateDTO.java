package com.B2B.EcommerceApp.UserService.dto.request;

import com.B2B.EcommerceApp.UserService.entity.Enum.UserStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class UserUpdateDTO {
    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private long userContactNumber;

    private String userPassword;

    private UserStatus userStatus;

}
