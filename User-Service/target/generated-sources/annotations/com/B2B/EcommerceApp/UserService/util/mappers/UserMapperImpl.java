package com.B2B.EcommerceApp.UserService.util.mappers;

import com.B2B.EcommerceApp.UserService.dto.UserDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserSaveDTO;
import com.B2B.EcommerceApp.UserService.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-07T10:49:17+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<UserDTO> userEntityListToUserDtoList(List<User> user) {
        if ( user == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( user.size() );
        for ( User user1 : user ) {
            list.add( userEntity_to_UserDTO( user1 ) );
        }

        return list;
    }

    @Override
    public UserDTO userEntity_to_UserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUserID( user.getUserID() );
        userDTO.setUserSyscoID( user.getUserSyscoID() );
        userDTO.setUserFirstName( user.getUserFirstName() );
        userDTO.setUserLastName( user.getUserLastName() );
        userDTO.setUserEmail( user.getUserEmail() );
        userDTO.setUserContactNumber( user.getUserContactNumber() );
        userDTO.setUserRole( user.getUserRole() );
        userDTO.setUserStatus( user.getUserStatus() );

        return userDTO;
    }

    @Override
    public List<UserDTO> PageEntityList_to_UserDTOList(Page<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>();
        for ( User user : users ) {
            list.add( userEntity_to_UserDTO( user ) );
        }

        return list;
    }

    @Override
    public User userSaveDTO_to_userEntity(UserSaveDTO userSaveDTO) {
        if ( userSaveDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUserFirstName( userSaveDTO.getUserFirstName() );
        user.setUserLastName( userSaveDTO.getUserLastName() );
        user.setUserEmail( userSaveDTO.getUserEmail() );
        user.setUserContactNumber( userSaveDTO.getUserContactNumber() );
        user.setUserRole( userSaveDTO.getUserRole() );
        user.setUserPassword( userSaveDTO.getUserPassword() );
        user.setUserStatus( userSaveDTO.getUserStatus() );

        return user;
    }
}
