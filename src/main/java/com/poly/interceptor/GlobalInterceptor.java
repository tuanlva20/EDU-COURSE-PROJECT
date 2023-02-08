package com.poly.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.service.CategoryService;
import com.poly.bean.Account;
import com.poly.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
    @Autowired
    CategoryService cateService;

    @Autowired
    AccountDAO aDao;

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,
                        ModelAndView modelAndView) {
            Account account = new Account();   
            
            String username = request.getRemoteUser();
            Optional<Account> existUserBySub = aDao.getAccountBySub(username);
            if(existUserBySub.isPresent()){
                account = existUserBySub.get();
            }else if(username != null && username.length() > 0){
                account = aDao.findById(username).get();
            }
            
            request.setAttribute("account", account);
            request.setAttribute("categories", cateService.getAll());
    };
}
