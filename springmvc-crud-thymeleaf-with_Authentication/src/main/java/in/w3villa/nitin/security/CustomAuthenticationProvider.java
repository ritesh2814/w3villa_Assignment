package in.w3villa.nitin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import in.w3villa.nitin.model.UserDetails;
import in.w3villa.nitin.repository.IUserDetails;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IUserDetails userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        // Fetch user details from database
        UserDetails user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Validate password
        	if(rawPassword.equalsIgnoreCase(user.getPassword())) {
            // If valid, return authentication token
            return new UsernamePasswordAuthenticationToken(
                    username,
                    rawPassword,
                    Collections.emptyList() // Roles or authorities can be added here
            );
        }

        throw new UsernameNotFoundException("Invalid username or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

