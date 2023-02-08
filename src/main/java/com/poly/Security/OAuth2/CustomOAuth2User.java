package com.poly.Security.OAuth2;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User{
    private OAuth2User oauth2USer;

    public CustomOAuth2User(OAuth2User oauth2uSer) {
        oauth2USer = oauth2uSer;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2USer.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2USer.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2USer.getAttribute("name");
    }
    
    public String getEmail(){
        return oauth2USer.<String>getAttribute("email");
    }
}
