package com.poly.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.poly.bean.Account;

@Service
public class ThymeleafService {
private static final String MAIL_TEMPLATE_BASE_NAME = "mail/MailMessages";
private static final String MAIL_TEMPLATE_PREFIX = "/templates/email/";
private static final String MAIL_TEMPLATE_SUFFIX = ".html";
private static final String UTF_8 = "UTF-8";

private static final String TEMPLATE_NAME = "email";
private static final String TEMPLATE_NAMEREGISTER = "templateRegisterMail";

private static TemplateEngine templateEngine;

static {
    templateEngine = emailTemplateEngine();
}

private static TemplateEngine emailTemplateEngine() {
    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(htmlTemplateResolver());
    templateEngine.setTemplateEngineMessageSource(emailMessageSource());
    return templateEngine;
}


private static ResourceBundleMessageSource emailMessageSource() {
    final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename(MAIL_TEMPLATE_BASE_NAME);
    return messageSource;
}

private static ITemplateResolver htmlTemplateResolver() {
    final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
    templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setCharacterEncoding(UTF_8);
    templateResolver.setCacheable(false);
    return templateResolver;
}

public String getContent(Account account) {
    final Context context = new Context();

    context.setVariable("name", account.getFullname());
    context.setVariable("project_name", "spring-email-with-thymeleaf Demo");

    return templateEngine.process(TEMPLATE_NAME, context);
}
public String getContent1(int code) {
    final Context context = new Context();
    context.setVariable("project_name1", "spring-email-with-thymeleaf Demo1");
    context.setVariable("code", code);
    return templateEngine.process(TEMPLATE_NAMEREGISTER, context);
}
}
