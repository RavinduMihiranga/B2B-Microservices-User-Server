package com.B2B.EcommerceApp.UserService.entity;


import com.B2B.EcommerceApp.UserService.entity.Enum.UserRole;
import com.B2B.EcommerceApp.UserService.entity.Enum.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID", length = 10, updatable = false)
    private int userID;

    @Column(name = "user_sysco_ID", length = 100, unique = true)
    private String userSyscoID;

    @Column(name = "user_first_name", length = 50, nullable = false)
    private String userFirstName;

    @Column(name = "user_last_name", length = 50, nullable = false)
    private String userLastName;

    @Column(name = "user_email", length = 70, nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_contactNumber", length = 10, nullable = false)
    private long userContactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", length = 20, nullable = false)
    private UserRole userRole;

    @Column(name = "user_password", length = 1000, nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", length = 20, nullable = false)
    private UserStatus userStatus = UserStatus.valueOf("inactive");
}
