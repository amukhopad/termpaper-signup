package ua.edu.ukma.termpapers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import ua.edu.ukma.termpapers.entity.enums.Role;
import ua.edu.ukma.termpapers.repository.DefaultUserRepository;
import ua.edu.ukma.termpapers.service.PlainPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private DefaultUserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public SecurityConfiguration(DefaultUserRepository userRepository,
                               PlainPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userRepository).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/",
            "/css/**",
            "/favicon.ico",
            "/user/register")
        .permitAll()
        .antMatchers("/user/all")
        .hasRole(Role.METHODIST.name())
        .antMatchers("/student/register")
        .hasAnyRole(Role.METHODIST.name(), Role.STUDENT.name())
        .antMatchers(
            "/coursework/new",
            "/teacher/register")
        .hasAnyRole(Role.METHODIST.name(), Role.TEACHER.name())
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/")
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .and()
        .exceptionHandling();
  }

  @Bean
  public AuthenticationManager customAuthenticationManager() throws Exception {
    return authenticationManager();
  }
}
