package org.goit.HorbatkoIvan.JD15.HW18.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("checkstyle:MissingJavadocType")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/**")
        .authenticated()
        .anyRequest()
        .permitAll())
        .httpBasic(withDefaults())
        .formLogin(withDefaults())
        .csrf(AbstractHttpConfigurer::disable);

    return http.build();
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User.withUsername("user").password(encoder().encode("password")).roles("USER").build();
    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

}
