package com.nnk.springboot.config;
import com.nnk.springboot.service.ClientUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security Configuration class establishes the main parameters of configuration the application
 * via Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    @Qualifier("clientUserDetailsService")
    private UserDetailsService clientUserDetailsService;

    /**
     *
     *  Setup of Security FilterChain bean which defines the endpoints to expose among other aspects of the security
     *  depending on the http requests prompted by the user.
     *
     *  This object is consulted then each time an access is requested.
     *
     *  By default: all the access is blocked by Spring Security, hence the configuration decides whether to grant access based on roles.
     *
     *  - RequestMatchers: allow to set the level of access control to the URI level for the respective roles/authorities.
     *  - Access to URIs are defined by authority levels: USER and ADMIN.
     *  - The logic of the filter rules follow a sequential order of execution.
     *  - The login and logout forms are used by the default Spring Security Configuration
     *
     *  - Other relevant features included: remindme and token expiration in a day max.
     *
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     *
     * @see <a href="https://spring.io/guides/topicals/spring-security-architecture">...</a>
     * @see <a href="https://www.baeldung.com/spring-security-login">...</a>
     * @see <a href="https://www.baeldung.com/spring-security-configuring-urls">...</a>
     * @see <a href="https://www.baeldung.com/spring-security-remember-me">...</a>
     * @see <a href="https://www.baeldung.com/spring-redirect-after-login">...</a>
     *
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
                .authorizeHttpRequests(auth->auth.requestMatchers("login", "/"))
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/",
                        "/css/**", //needs to be included, otherwise the site won't render the bootstrap style
                        "/login/**",
                        "/bidList/**",
                        "/rating/**",
                        "/curvePoint/**",
                        "/ruleName/**",
                        "/trade/**",
                        "/app/error",
                        "/app-logout").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                )
                .authorizeHttpRequests(auth->auth.requestMatchers("**", "/", "/**").hasAuthority("ROLE_ADMIN"))
                .formLogin(
                        form -> form   //reading login : https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html
                                .usernameParameter("username")
                                .defaultSuccessUrl("/bidList/list", true)
                                .failureUrl("/app/error")
                                .permitAll())
                .rememberMe(rememberMe -> rememberMe
                       .key("uniqueAndSecret")
                       .tokenValiditySeconds(86400))
                .build();
    }

    /**
     * Once called provides the password encoder object based on BCrypt Hashing.
     * @return BcryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Builds an Authentication Manager required by the UserDetailsService to run the login system.
     * It works under the context of clientUserDetailsService.
     * It is based on the http transaction and the password hashed in bcrypt so:
     * @param http
     * @param bCryptPasswordEncoder
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(clientUserDetailsService).passwordEncoder(bCryptPasswordEncoder);

        return authenticationManagerBuilder.build();
    }


}