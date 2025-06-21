package ro.mta.implauthzserver.model;


import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import ro.mta.baseauthzserver.entity.BaseUser;
import ro.mta.baseauthzserver.model.BaseUserDetails;

import java.util.Collection;

@Getter
@Builder
public class CustomUserDetails implements BaseUserDetails {

    private final String id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;

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
    public BaseUser getUser() {
        return null;
    }
}