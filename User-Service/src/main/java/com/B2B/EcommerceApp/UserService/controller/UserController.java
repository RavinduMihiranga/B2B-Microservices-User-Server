package com.B2B.EcommerceApp.UserService.controller;

import com.B2B.EcommerceApp.UserService.dto.UserDTO;
import com.B2B.EcommerceApp.UserService.dto.paginated.PaginatedResponseUserDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserLogInDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserSaveDTO;
import com.B2B.EcommerceApp.UserService.dto.request.UserUpdateDTO;
import com.B2B.EcommerceApp.UserService.service.UserService;
import com.B2B.EcommerceApp.UserService.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<StandardResponse> saveUser(@RequestBody UserSaveDTO userSaveDTO) {
        UserDTO userDTO = userService.saveUser(userSaveDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "saved successfully", userDTO), HttpStatus.CREATED
        );
    }

    @PostMapping(
            path = "/login"
    )
    public ResponseEntity<StandardResponse> LogInUser(@RequestBody UserLogInDTO userLogInDTO) {
        UserDTO userDTO = userService.logInUser(userLogInDTO);
        return new ResponseEntity<>(
                new StandardResponse(200, "Logged in successfully", userDTO), HttpStatus.CREATED
        );
    }


    @DeleteMapping(
            params = "sysco_id"
    )
    public ResponseEntity<StandardResponse> deleteUser(@RequestParam(value = "sysco_id") String userSyscoID) {
        return new ResponseEntity<>(
                new StandardResponse(204, "deleted successfully", userService.deleteUser(userSyscoID)), HttpStatus.OK);
    }

    @GetMapping(
            params = "sysco_id"
    )
    public ResponseEntity<StandardResponse> getUserBySyscoId(@RequestParam(value = "sysco_id") String userSyscoID) {
        return new ResponseEntity<>(
                new StandardResponse(200, "found successfully", userService.getUserBySyscoId(userSyscoID)), HttpStatus.OK);
    }

//    @GetMapping(
//            path = "/get-user-by-id/{id}"
//    )
//    public ResponseEntity<StandardResponse> getUserById(@PathVariable(value = "id") int userID) {
//        return new ResponseEntity<>(
//                new StandardResponse(200, "found successfully", userService.getUserById(userID)), HttpStatus.OK);
//    }

    @PutMapping(
            params = "sysco_id"
    )
    public ResponseEntity<StandardResponse> updateUserBySyscoId(@RequestParam(value = "sysco_id") String userSyscoID, @RequestBody UserUpdateDTO userUpdateDTO) {
        return new ResponseEntity<>(
                new StandardResponse(204, "updated successfully", userService.updateUser(userSyscoID, userUpdateDTO)), HttpStatus.OK);
    }

    @GetMapping(
            path = "/get-users-by-userRole",
            params = {
                    "userRole",
                    "userStatus",
                    "page",
                    "size"
            }

    )
    public ResponseEntity<StandardResponse> getUsersByUserRole(
            @RequestParam(value = "userRole") String userRole,
            @RequestParam(value = "userStatus") String userStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedResponseUserDTO paginatedResponseUserDTO = null;
        if (userRole.equalsIgnoreCase("all") && (userStatus.equalsIgnoreCase("all"))){
            paginatedResponseUserDTO = userService.getAllUsers(page, size);
        } else if(userRole.equalsIgnoreCase("all") && (!userStatus.equalsIgnoreCase("all"))){
            paginatedResponseUserDTO = userService.getAllUsersByUserStatus(userStatus, page, size);
        }else if(!userRole.equalsIgnoreCase("all") && (userStatus.equalsIgnoreCase("all"))){
            paginatedResponseUserDTO = userService.getAllUsersByUserRole(userRole, page, size);
        }else {
            paginatedResponseUserDTO = userService.getAllUsersByUserRoleWithPaginated(userRole, userStatus, page, size);
        }

        return new ResponseEntity<>(
                new StandardResponse(200, "Found users successfully", paginatedResponseUserDTO), HttpStatus.OK);
    }


}

