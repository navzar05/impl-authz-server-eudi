package ro.mta.implauthzserver.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.mta.baseauthzserver.model.BaseUserDetails;
import ro.mta.baseauthzserver.service.BaseUserDetailsService;
import ro.mta.implauthzserver.entity.User;
import ro.mta.implauthzserver.model.CustomUserDetails;
import ro.mta.implauthzserver.repository.UserRepository;


import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements BaseUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public BaseUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return CustomUserDetails.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList()))
                .enabled(user.isEnabled())
                .build();
    }
}