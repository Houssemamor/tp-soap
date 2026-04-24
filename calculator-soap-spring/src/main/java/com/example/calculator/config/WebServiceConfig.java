package com.example.calculator.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

/**
 * Configuration class for Spring Web Services (SOAP).
 * Configures the message dispatcher servlet for SOAP requests.
 */
@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * Register the Message Dispatcher Servlet.
     * SOAP requests matching the pattern are handled by this servlet.
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        ServletRegistrationBean<MessageDispatcherServlet> registration = 
            new ServletRegistrationBean<>(servlet, "/ws/*");
        registration.setName("messageDispatcherServlet");
        return registration;
    }
}
