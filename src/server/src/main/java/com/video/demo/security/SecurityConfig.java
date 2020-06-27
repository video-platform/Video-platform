package com.video.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.demo.security.filter.JwtAuthenticationFilter;
import com.video.demo.security.filter.LoginFilter;
import com.video.demo.security.handlers.JwtAuthenticationFailureHandler;
import com.video.demo.security.handlers.LoginAuthenticationSuccessHandler;
import com.video.demo.security.providers.JwtAuthenticationProvider;
import com.video.demo.security.providers.LoginAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;

    @Autowired
    private LoginAuthenticationProvider provider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

    @Autowired
    private HeaderTokenExtractor headerTokenExtractor;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception{
        return super.authenticationManagerBean();
    }

    private LoginFilter loginFilter() throws Exception{
        LoginFilter loginFilter = new LoginFilter("/signin", loginAuthenticationSuccessHandler, null);
        loginFilter.setAuthenticationManager(super.authenticationManager());

        return loginFilter;
    }

    protected JwtAuthenticationFilter jwtFilter() throws Exception{
        FilterSkipMatcher matcher = new FilterSkipMatcher(Arrays.asList("/signin"), "/member/**");
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(matcher, jwtAuthenticationFailureHandler, headerTokenExtractor);
        jwtAuthenticationFilter.setAuthenticationManager(super.authenticationManagerBean());

        return jwtAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(this.provider)
                .authenticationProvider(this.jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
