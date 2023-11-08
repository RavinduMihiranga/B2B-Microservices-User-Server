package com.B2B.EcommerceApp.UserService.util.mappers;


import com.B2B.EcommerceApp.UserService.dto.UserDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserSaveDTO;
import com.B2B.EcommerceApp.UserService.entity.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserDTO> userEntityListToUserDtoList(List<User> user);

    UserDTO userEntity_to_UserDTO(User user);

    List<UserDTO> PageEntityList_to_UserDTOList(Page<User> users);

    User userSaveDTO_to_userEntity(UserSaveDTO userSaveDTO);
}

