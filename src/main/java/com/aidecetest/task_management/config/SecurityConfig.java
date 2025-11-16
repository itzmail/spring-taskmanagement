package com.aidecetest.task_management.config;

import com.aidecetest.task_management.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthFilter jwtAuthFilter;

  public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
    this.jwtAuthFilter = jwtAuthFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(authz ->
        authz
          .requestMatchers(
            "/api/auth/login",
            "/swagger-ui.html", // Halaman utama
            "/swagger-ui/**", // Aset statis (CSS/JS)
            "/v3/api-docs/**", // Definisi API
            "/webjars/**", // Sumber daya statis (Penting untuk Swagger)
            "/error" // Penting untuk error handling
          )
          .permitAll() // Izinkan semua akses ke jalur-jalur di atas
          // Semua request lain harus diautentikasi
          .anyRequest()
          .authenticated()
      )
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .addFilterBefore(
        jwtAuthFilter,
        UsernamePasswordAuthenticationFilter.class
      );

    return http.build();
  }
}
