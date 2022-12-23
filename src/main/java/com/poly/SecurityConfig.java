package com.poly;

import com.poly.dao.AccountDAO;
import com.poly.service.SecuriryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    SecuriryService userService;

    @Autowired
    AccountDAO accountDAO;

    /* Quản lý nguồn dữ liệu người dùng */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userService);
    }

    /* Phân quyền sử dụng và hình thức đăng nhập */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //CSRF, CORS
        http.csrf().disable();
        // Phân quyền sử dụng
        // http.authorizeRequests()
        //     .antMatchers("/admin/**").hasAnyRole("DIRE","STAF")
        //     .antMatchers("/home/user").hasAnyRole("DIRE","STAF")
        //     .antMatchers("/order/**","/user").authenticated()
        //     .antMatchers("/**").permitAll()
        //     .anyRequest().permitAll();
            
        // .antMatchers("/rest/authorities","/rest/authorities/**").hasRole("DIRE")
        // http.authorizeRequests().anyRequest().permitAll();

        //Điểu khiển lỗi không đúng vai trò
        http.exceptionHandling()
            .accessDeniedPage("/security/access/denied");

        //Giao diện đăng nhập
        http.formLogin()
            .loginPage("/security/login/form")
            .loginProcessingUrl("/security/login")
            .defaultSuccessUrl("/user",false)
            .failureUrl("/security/login/error")
            .usernameParameter("username")
            .passwordParameter("password");

        // http.rememberMe()
        //     .tokenValiditySeconds(86400);
        //     .rememberMeParameter("remember");

        http.logout()
            .logoutUrl("/logoff")
            .logoutSuccessUrl("/security/logoff/success");

        //OAuth2 - Đăng nhập từ mạng xã hội
        http.oauth2Login()
            .loginPage("/security/login/form")
            .defaultSuccessUrl("/courses/",false)
            .failureUrl("/security/login/error")
            .authorizationEndpoint()
                .baseUri("/oauth2/authorization");
    }

    // Mã hóa mật khẩu
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cho phép truy xuất REST API từ bên ngoài (domain khác)
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user");
    }

}
