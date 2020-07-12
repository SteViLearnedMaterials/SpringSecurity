package com.example.security.config;

import com.example.security.dto.dictionary.Role;
import com.example.security.dto.dictionary.UserPermission;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.security.dto.dictionary.UserPermission.COURSE_WRITE;

@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js*").permitAll()
                .antMatchers("/api/**").hasAnyRole(Role.STUDENT.name(), Role.ADMIN.name(), Role.ADMIN_TRAINEE.name())
                .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(Role.ADMIN.name(), Role.ADMIN_TRAINEE.name())
                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("student")
                .password(passwordEncoder.encode("password"))
                .authorities(Role.STUDENT.grantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .authorities(Role.ADMIN.grantedAuthorities())
                .build();

        UserDetails adminTrainee = User.builder()
                .username("adminTrainee")
                .password(passwordEncoder.encode("password"))
                .authorities(Role.ADMIN_TRAINEE.grantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(user, admin, adminTrainee);
    }
}
