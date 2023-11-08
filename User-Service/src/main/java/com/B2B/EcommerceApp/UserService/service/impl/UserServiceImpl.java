package com.B2B.EcommerceApp.UserService.service.impl;


import com.B2B.EcommerceApp.UserService.dto.UserDTO;
import com.B2B.EcommerceApp.UserService.dto.paginated.PaginatedResponseUserDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserLogInDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserSaveDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserUpdateDTO;
import com.B2B.EcommerceApp.UserService.entity.Enum.UserRole;
import com.B2B.EcommerceApp.UserService.entity.Enum.UserStatus;
import com.B2B.EcommerceApp.UserService.entity.User;
import com.B2B.EcommerceApp.UserService.exception.DuplicateKeyException;
import com.B2B.EcommerceApp.UserService.exception.NotFoundException;
import com.B2B.EcommerceApp.UserService.exception.Unauthorized;
import com.B2B.EcommerceApp.UserService.repo.UserRepo;
import com.B2B.EcommerceApp.UserService.service.UserService;
import com.B2B.EcommerceApp.UserService.util.mappers.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserMapper userMapper;

    

    @Override
    public UserDTO saveUser(UserSaveDTO userSaveDTO) {
//        User user = modelMapper.map(userSaveDTO, User.class);
        User user = userMapper.userSaveDTO_to_userEntity(userSaveDTO);
        if (!userRepo.existsByUserEmail(userSaveDTO.getUserEmail())) {
            int occurrences = userRepo.countAllByUserFirstNameEquals(userSaveDTO.getUserFirstName());
            String[] names = userSaveDTO.getUserFirstName().split(" ");
            int num = occurrences + 1;
            String userSysID = names[0] + num;
            user.setUserSyscoID(userSysID);
            userRepo.save(user);
            return userMapper.userEntity_to_UserDTO(user);
        } else {
            throw new DuplicateKeyException("Email already exists");
        }
    }

    @Override
    public UserDTO logInUser(UserLogInDTO userLogInDTO) {
        String email = userLogInDTO.getUserEmail();
        String password = userLogInDTO.getUserPassword();
        if (userRepo.existsByUserEmailEqualsAndUserPasswordEquals(email, password)) {
            User user = userRepo.findByUserEmailEquals(email);
            if(user.getUserStatus().toString().equalsIgnoreCase("inactive")){
                throw new Unauthorized("Inactive user");
            } else {
                return userMapper.userEntity_to_UserDTO(user);
            }
        } else {
            throw new Unauthorized("Credentials are incorrect");
        }
    }

    @Override
    public PaginatedResponseUserDTO getAllUsers(int page, int size) {
        Page<User> users = userRepo.findAll(PageRequest.of(page, size));
        if (!users.isEmpty()) {
            return new PaginatedResponseUserDTO(
                    userMapper.PageEntityList_to_UserDTOList(users),
                    userRepo.count()
            );
        } else {
            throw new NotFoundException("Users Not Found");
        }
    }

    @Override
    public PaginatedResponseUserDTO getAllUsersByUserStatus(String userStatus, int page, int size) {
        UserStatus userStatus1 = UserStatus.valueOf(userStatus);
        Page<User> users = userRepo.findAllByUserStatusEquals(userStatus1, PageRequest.of(page, size));
        if (!users.isEmpty()) {
            return new PaginatedResponseUserDTO(
                    userMapper.PageEntityList_to_UserDTOList(users),
                    userRepo.countAllByUserStatusEquals(userStatus1)
            );
        } else {
            throw new NotFoundException("Users Not Found");
        }
    }

    @Override
    public PaginatedResponseUserDTO getAllUsersByUserRole(String userRole, int page, int size) {
        UserRole userRole1 = UserRole.valueOf(userRole);
        Page<User> users = userRepo.findAllByUserRoleEquals(userRole1,PageRequest.of(page, size));
        if (!users.isEmpty()) {
            return new PaginatedResponseUserDTO(
                    userMapper.PageEntityList_to_UserDTOList(users),
                    userRepo.countAllByUserRoleEquals(userRole1)
            );
        } else {
            throw new NotFoundException("Users Not Found");
        }
    }


    @Override
    public UserUpdateDTO updateUser(String userSyscoID, UserUpdateDTO userUpdateDTO) {
        if (userRepo.existsByUserSyscoIDEquals(userSyscoID)) {
            User user = userRepo.findByUserSyscoID(userSyscoID);
            user.setUserFirstName(userUpdateDTO.getUserFirstName());
            user.setUserLastName(userUpdateDTO.getUserLastName());
            user.setUserContactNumber(userUpdateDTO.getUserContactNumber());
            user.setUserStatus(userUpdateDTO.getUserStatus());
            if(!user.getUserEmail().equalsIgnoreCase(userUpdateDTO.getUserEmail())){
                if (userRepo.existsByUserEmail(userUpdateDTO.getUserEmail())){
                    throw new DuplicateKeyException("Email already exists");
                }
            }
            user.setUserEmail(userUpdateDTO.getUserEmail());
            if(userUpdateDTO.getUserPassword() != null){
                user.setUserPassword(userUpdateDTO.getUserPassword());
            }
            userRepo.save(user);
            return userUpdateDTO;

        } else {
            throw new NotFoundException("User Not Found");
        }
    }

//    @Override
//    public UserDTO getUserById(int userID) {
//        if (userRepo.existsById(userID)) {
//            User user = userRepo.getReferenceById(userID);
//            return modelMapper.map(user, UserDTO.class);
//        } else {
//            throw new NotFoundException("User Not Found");
//        }
//
//    }

    @Override
    public String deleteUser(String userSyscoID) {
        if (userRepo.existsByUserSyscoIDEquals(userSyscoID)) {
            User user = userRepo.findByUserSyscoID(userSyscoID);
            userRepo.delete(user);
            return "Deleted Successfully";
        } else {
            throw new NotFoundException("User Not Found");
        }
    }

    @Override
    public PaginatedResponseUserDTO getAllUsersByUserRoleWithPaginated(String userRole, String userStatus, int page, int size) {
        UserRole userRole1 = UserRole.valueOf(userRole);
        UserStatus userStatus1 = UserStatus.valueOf(userStatus);
        Page<User> users = userRepo.findAllByUserRoleEqualsAndUserStatusEquals(userRole1, userStatus1, PageRequest.of(page, size));
        if (!users.isEmpty()) {
            return new PaginatedResponseUserDTO(
                    userMapper.PageEntityList_to_UserDTOList(users),
                    userRepo.countAllByUserRoleEqualsAndUserStatusEquals(userRole1, userStatus1)
            );
        } else {
            throw new NotFoundException("Users Not Found");
        }
    }

    @Override
    public UserDTO getUserBySyscoId(String userSyscoID) {
        boolean exists = userRepo.existsByUserSyscoIDEquals(userSyscoID);
        if (exists) {
            User user = userRepo.findByUserSyscoID(userSyscoID);
            return userMapper.userEntity_to_UserDTO(user);
        } else {
            throw new NotFoundException("User Not Found");
        }
    }

}
