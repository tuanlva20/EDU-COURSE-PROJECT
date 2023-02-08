package com.poly.service;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.poly.bean.Account;
import com.poly.dao.AccountDAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class SecuriryService implements UserDetailsService {
    @Autowired
    BCryptPasswordEncoder pe;

    @Autowired
    AccountDAO accountDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account acc = accountDAO.findById(username).get();
            System.out.println("-----------------------------------");
            String password =acc.getPassword();
            String[] roles=acc.getAuthorities().stream()
                            .map(a -> a.getRole().getId())
                            .collect(Collectors.toList())
                            .toArray(new String[0]);
            
            return User.withUsername(username)
                    .password(password)
                    .roles(roles).build();
        } catch (Exception e) {
            throw new UsernameNotFoundException(username+"not found");
        }
    }

    public void loginFormOAuth2(OAuth2AuthenticationToken oauth2){
        // String fullname=oauth2.getPrincipal().getAttribute("name");
        String email=oauth2.getPrincipal().getAttribute("email");
        String password=Long.toHexString(System.currentTimeMillis());

        UserDetails user=User.withUsername(email)
                        .password(pe.encode(password))
                        .roles("CUST").build();
        Authentication auth=new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
