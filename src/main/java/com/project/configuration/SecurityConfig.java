package com.project.configuration;

import com.project.filters.RequestResponseLoggingFilter;
import com.project.security.AuthProviderImpl;
import com.project.security.CustomAuthDeniedHandler;
import com.project.security.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(1)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RequestResponseLoggingFilter requestResponseLoggingFilter;
    private final CustomAuthDeniedHandler customAuthDeniedHandler;
    private final AuthProviderImpl authProvider;

    @Bean
    public LoginFilter authenticationFilter() throws Exception {
        LoginFilter authenticationFilter = new LoginFilter();
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);//TODO: добавить смысла , разграничения

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()


                //.and()
                //   .formLogin()
                //    .failureHandler(customAuthDeniedHandler)//.accessDeniedHandler("/auth/error")
                //     .loginProcessingUrl("/login")

                .and()
                .addFilterBefore(
                        authenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .logout()
                .and()
                .csrf().disable()
                .addFilterAfter(requestResponseLoggingFilter.hiddenHttpMethodFilter(), BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

}
