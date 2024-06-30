package dio.springboot.security.dio.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_USER("ROLE_USER");


    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }



    @Override
    public String getAuthority() {
        return name();
    }
}
