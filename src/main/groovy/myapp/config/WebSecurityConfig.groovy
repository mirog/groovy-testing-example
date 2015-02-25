package myapp.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) {
        http
                .authorizeRequests()
                .antMatchers('/').permitAll()
                .antMatchers('/hello/*').permitAll()
                .anyRequest().authenticated()
        .and()
                .formLogin()
                .loginPage('/login')
                .defaultSuccessUrl('/weather')
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher('/logout')).logoutSuccessUrl('/login')
                .permitAll()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .inMemoryAuthentication()
                .withUser('Torun')
                .password('user')
                .roles('USER')
                .and()
                .withUser('Tokyo')
                .password('user')
                .roles('USER')
                .and()
                .withUser('Manchester')
                .password('user')
                .roles('USER')
                .and()
                .withUser('Torunn')
                .password('user')
                .roles('USER')
    }
}
