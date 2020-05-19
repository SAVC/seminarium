package org.ideacreation.seminarium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideacreation.seminarium.domain.model.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String username;
    private String email;
    private Role role;
    private String nickname;
}
