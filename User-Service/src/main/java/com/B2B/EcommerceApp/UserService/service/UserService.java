package com.B2B.EcommerceApp.UserService.service;

import com.B2B.EcommerceApp.UserService.dto.UserDTO;
import com.B2B.EcommerceApp.UserService.dto.paginated.PaginatedResponseUserDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserLogInDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserSaveDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserUpdateDTO;

public interface UserService {
    UserDTO saveUser(UserSaveDTO userSaveDTO);

    UserUpdateDTO updateUser(String userSyscoID, UserUpdateDTO userUpdateDTO);

//    public UserDTO getUserById(int userID);

    String deleteUser(String userSyscoID);

    PaginatedResponseUserDTO getAllUsersByUserRoleWithPaginated(String userRole, String userStatus, int page, int size);

    UserDTO getUserBySyscoId(String userSyscoID);

    UserDTO logInUser(UserLogInDTO userLogInDTO);

    PaginatedResponseUserDTO getAllUsers(int page, int size);

    PaginatedResponseUserDTO getAllUsersByUserStatus(String userStatus, int page, int size);

    PaginatedResponseUserDTO getAllUsersByUserRole(String userRole, int page, int size);
}
