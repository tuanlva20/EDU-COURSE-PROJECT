package com.poly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.service.CategoryService;
import com.poly.bean.Account;
import com.poly.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
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
            request.setAttribute("categories", cateService.getAll());
            String username = request.getRemoteUser();
            Account account = new Account();         
            if(username != null && username.length() > 0){
                account = aDao.findById(username).get();
                request.setAttribute("account", account);
            }else{
                    request.setAttribute("account", account);
            }
    };
}
