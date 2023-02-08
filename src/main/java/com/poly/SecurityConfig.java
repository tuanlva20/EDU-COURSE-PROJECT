package com.poly;

import com.poly.Security.OAuth2.CustomOAuth2User;
import com.poly.dao.AccountDAO;
import com.poly.service.AccountService;
import com.poly.service.SecuriryService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    SecuriryService userService;

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    AccountService accountService;

    @Autowired
    OAuth2UserService oAuth2UserService;

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
        http.authorizeRequests()
            .antMatchers("/admin/**").hasAnyRole("DIRE")
            .antMatchers("/home/user").hasAnyRole("DIRE","STAF")
            .antMatchers("/order/**","/user").authenticated()
            .antMatchers("/**").permitAll()
            .anyRequest().permitAll();
            
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
            // .defaultSuccessUrl("/user/",false)
            .failureUrl("/security/login/error")
            // .authorizationEndpoint()
                // .baseUri("/oauth2/authorization")
            // .userInfoEndpoint()
            // .userService(oAuth2UserService)
            // .and()
            .successHandler(new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                        Authentication authentication) throws IOException, ServletException {
                    DefaultOidcUser oauth2User = (DefaultOidcUser) authentication.getPrincipal();
                    accountService.processOAuthPostLogin(oauth2User);
                    response.sendRedirect("/user");
                }
            });
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
