package org.ideacreation.seminarium.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideacreation.seminarium.domain.model.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private Role role;

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String surname;
}
