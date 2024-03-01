package com.github.rflowcode.springbootangularstartertemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class SpringBootAngularStarterTemplateApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootAngularStarterTemplateApplication.class);
        application.addListeners(new ApplicationPidFileWriter());

        application.run(args);
    }
}
