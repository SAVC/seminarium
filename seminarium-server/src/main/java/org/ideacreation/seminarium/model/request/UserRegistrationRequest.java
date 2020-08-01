package org.ideacreation.seminarium.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationRequest {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String nickname;
}
