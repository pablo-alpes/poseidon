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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    //We create a security filter bean for the endpoints to expose.
    //By default; all the access is blocked by Spring Security. We grant access to the URI defined below.
    //AntMatchers allow to set the level of access control to the path level.
    //Other rules can be added: hasrole, authenticated.
    // All these rules (filters) need to follow a sequential order for
    // execution.
    //https://www.baeldung.com/spring-security-configuring-urls
    @Autowired
    @Qualifier("clientUserDetailsService")
    private UserDetailsService clientUserDetailsService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //https://www.baeldung.com/spring-security-login

        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/**").hasRole("ADMIN");
                })
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/css/**", //needs to be included, otherwise the site won't render the bootstrap style
                        "/",
                        "/login/**",
                        "/bidList/**",
                        "/rating/**",
                        "/curvePoint/**",
                        "/ruleName/**",
                        "/trade/**",
                        "/app/error",
                        "/app-logout").hasRole("USER"))
                .formLogin(
                        form -> form   //reading login : https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html
                                //.formlogin is excluded to use the one by default : https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html#servlet-authentication-form-custom
                                .usernameParameter("username")
                                .defaultSuccessUrl("/bidList/list", true) //https://www.baeldung.com/spring-redirect-after-login
                                .failureUrl("/app/error")
                                .permitAll())
                .rememberMe(rememberMe -> rememberMe // https://www.baeldung.com/spring-security-remember-me
                       .key("uniqueAndSecret")
                       .tokenValiditySeconds(86400)) //TODO - Bug if enabled it does not logs in
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(clientUserDetailsService).passwordEncoder(bCryptPasswordEncoder);

        return authenticationManagerBuilder.build();
    }


}