package com.revature.util;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.revature.service.MessageService;
import com.revature.service.ReportService;
import com.revature.service.UserService;
import com.revature.service.ChatroomService;
 
@Configuration
public class TestContext {
 
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
 
        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
 
        return messageSource;
    }
 
    @Bean
    public MessageService messageService() {
        return Mockito.mock(MessageService.class);
    }
    
    @Bean
    public ChatroomService chatroomService() {
        return Mockito.mock(ChatroomService.class);
    }
    
    @Bean
    public ReportService reportService() {
        return Mockito.mock(ReportService.class);
    }
    
    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
}
