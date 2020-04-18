package com.project.security;


import com.project.entities.User;
import com.project.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO: надо сделать чтение auth из JSONa
        log.error("Try to authenticate: {} ", authentication.getPrincipal().toString());

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> authorities = new ArrayList<>();
        User user = userRepository.findTopByName(name)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not Found " + authentication.toString()));

        log.info(user.getName() + " " + user.getPassword());

        if (password.equals(user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user, null, authorities);
        } else {
            throw new AuthenticationServiceException("Invalid Password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
