package in.codingage.blooms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // ✅ Import this
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter jwtFilter) throws Exception{
        httpSecurity
                .csrf(csrf -> csrf.disable()) // api application csrf disable
                .authorizeHttpRequests(
                        auth -> auth
                                // Allow all preflight OPTIONS requests for CORS
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                                .requestMatchers("/api/account/signup").permitAll()
                                .requestMatchers("/api/account/login").permitAll()
                                .requestMatchers("/api/blogs/all", "/api/blogs/**").permitAll()
                                .requestMatchers("/api/categories/all", "/api/categories/**").permitAll() // ✅ Categories ke liye bhi allow karein
                                .requestMatchers("/api/subcategories/all", "/api/subcategories/**").permitAll()
                                .requestMatchers("/swagger-ui.html").permitAll()
                                .requestMatchers("/swagger-ui/index.html").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}