package com.board.spring_board.security;


import com.board.spring_board.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;

@Getter
public class PrincipalUser implements UserDetails {

    private User user;
    private String email;
    private String password;
    private int enabled;

    public PrincipalUser(User user) {
        this.user = user;
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(enabled == 0) {
            return false;
        }
        return true;
    }
}
