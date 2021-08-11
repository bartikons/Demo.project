package home.project.demo.Security.services;

import home.project.demo.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Setter
@Getter
public class UserPrinciple implements UserDetails {
    private Long id;
    private String username;
    private String password;

    public UserPrinciple(Long id, String username) {
    this.username=username;
    this.id=id;
    }

    public UserPrinciple() {

    }

    public static UserPrinciple build(User user) {
        return new UserPrinciple(
                user.getId(),
                user.getUsername()
        );

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
