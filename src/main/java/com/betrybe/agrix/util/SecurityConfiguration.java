package com.betrybe.agrix.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for defining security settings in the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  /**
   * Defines a security filter chain configuration.
   *
   * @param httpSecurity The HTTP security configuration to customize.
   * @return A security filter chain configuration.
   * @throws Exception If an error occurs while configuring security.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .csrf(AbstractHttpConfigurer::disable)
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(authorize -> authorize
          .requestMatchers(HttpMethod.POST, "/persons").permitAll()
          .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
          .anyRequest().authenticated()
      )
      .build();
  }

  /**
   * Provides an authentication manager.
   *
   * @param authenticationConfiguration The authentication configuration to use.
   * @return An authentication manager.
   * @throws Exception If an error occurs while obtaining the authentication manager.
   */
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * Provides a password encoder for securing user passwords.
   *
   * @return A password encoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
