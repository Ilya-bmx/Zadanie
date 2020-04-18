package com.project.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String requestBody;

        response.setHeader("Access-Control-Allow-Origin","*");

        try {
            request.getReader().lines().forEach(log::info);

        //TODO: из request передавать в этот метод
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken("user", "user");

            // Allow subclasses to set the "details" property
            setDetails(request, token);

            return this.getAuthenticationManager().authenticate(token);
        } catch(IOException e) {
            log.error("error auth filter", e);
            throw new RuntimeException("awawdada");
        }
    }
}
