package com.example.dec2021springboot.security;

import com.example.dec2021springboot.dao.CustomerDAO;
import com.example.dec2021springboot.models.Customer;
import com.example.dec2021springboot.security.filters.CustomFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
@EnableWebMvc
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomerDAO customerDAO;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    public CustomFilter customFilter() {
        CustomFilter customFilter = new CustomFilter(customerDAO);
        return customFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            System.out.println(username);
            Customer customer = customerDAO.findByLogin(username);
            return new User(username, customer.getPassword(), Arrays.asList(new SimpleGrantedAuthority(customer.getRole())));
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.csrf().disable();

        http = http.cors().configurationSource(corsConfigurationSource()).and();
        http = http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/", "/open").permitAll()
                .antMatchers(HttpMethod.POST, "/save").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()

                //eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YXN5YSJ9.4yP7y4wmPUglpo6KyaS-NP0Q1pcmYcvm3k3571w7CTYjIYgCdftSwboPIB0dR9UrXNcjImLMRa596AaaG18Edg - username
                .antMatchers(HttpMethod.GET, "/secure").hasAnyRole("ADMIN", "USER")
                .and();

        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        http = http.addFilterBefore(customFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:3000"));
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.HEAD.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
