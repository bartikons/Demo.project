package home.project.demo.configuration;

import home.project.demo.Security.JWT.JwtAuthEntryPoint;
import home.project.demo.Security.JWT.JwtAuthTokenFilter;
import home.project.demo.Security.JWT.JwtProvider;
import home.project.demo.Security.services.CustomAuthenticationProvider;
import home.project.demo.services.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider customAuthProvider;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtProvider tokenProvider;
    private final UserDetailServiceImpl userDetailsService;

    public WebSecurityConfiguration(JwtProvider tokenProvider, UserDetailServiceImpl userDetailsService, JwtAuthEntryPoint jwtAuthEntryPoint, CustomAuthenticationProvider customAuthProvider) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.customAuthProvider = customAuthProvider;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter(tokenProvider, userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/api/user/logIn").permitAll()
                .antMatchers("/api/user/CreateAccount").permitAll()
                .antMatchers("/api/user/getUserVideo").permitAll()
                .antMatchers("/api/Video/getVideo").permitAll()
                .antMatchers("/api/Video/getComment").permitAll()
                .antMatchers("/**").fullyAuthenticated()
                .and().cors()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
    }

}

