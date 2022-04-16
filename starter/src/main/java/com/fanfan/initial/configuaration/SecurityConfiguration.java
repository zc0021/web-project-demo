package com.fanfan.initial.configuaration;

import com.fanfan.initial.SensitiveKeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private Environment environment;

    @Bean
    public PasswordEncoder myEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String matches = environment.getProperty(SensitiveKeyProperties.HTTP_SECURITY_MATCH);
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(matches.split(",")).hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.logout().deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        String username = environment.getProperty(SensitiveKeyProperties.HTTP_SECURITY_USER);
        String password = environment.getProperty(SensitiveKeyProperties.HTTP_SECURITY_PWD);
        auth.inMemoryAuthentication().withUser(username).password(myEncoder().encode(password)).roles("USER");
    }
}
