package com.B2B.EcommerceApp.UserService.repo;


import com.B2B.EcommerceApp.UserService.entity.Enum.UserRole;
import com.B2B.EcommerceApp.UserService.entity.Enum.UserStatus;
import com.B2B.EcommerceApp.UserService.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Integer> {

    boolean existsByUserSyscoIDEquals(String userSyscoID);

    User findByUserSyscoID(String userSyscoID);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserEmailEqualsAndUserPasswordEquals(String email, String password);

    int countAllByUserFirstNameEquals(String userFirstName);

    User findByUserEmailEquals(String email);

    Page<User> findAllByUserRoleEqualsAndUserStatusEquals(UserRole userRole1, UserStatus userStatus1,Pageable pageable);

    long countAllByUserRoleEqualsAndUserStatusEquals(UserRole userRole1, UserStatus userStatus1);

    Page<User> findAllByUserStatusEquals(UserStatus userStatus1, Pageable pageable);

    long countAllByUserStatusEquals(UserStatus userStatus1);

    Page<User> findAllByUserRoleEquals(UserRole userRole1, PageRequest of);

    long countAllByUserRoleEquals(UserRole userRole1);
}
