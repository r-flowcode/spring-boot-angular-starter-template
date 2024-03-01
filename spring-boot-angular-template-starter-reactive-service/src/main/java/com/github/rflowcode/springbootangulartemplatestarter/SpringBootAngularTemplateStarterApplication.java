package com.github.rflowcode.springbootangulartemplatestarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class SpringBootAngularTemplateStarterApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootAngularTemplateStarterApplication.class);
        application.addListeners(new ApplicationPidFileWriter());

        application.run(args);
    }
}
