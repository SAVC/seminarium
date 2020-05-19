package org.ideacreation.seminarium.domain.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, HELPER, GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
