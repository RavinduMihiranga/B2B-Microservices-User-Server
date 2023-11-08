package com.B2B.EcommerceApp.UserService.dto.paginated;

import com.B2B.EcommerceApp.UserService.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseUserDTO {
    List<UserDTO> list;
    private long dataCount;
}
