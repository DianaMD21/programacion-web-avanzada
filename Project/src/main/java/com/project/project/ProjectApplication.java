package com.project.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ProjectApplication.class, args);
        ApplicationContext applicationContext = SpringApplication.run(ProjectApplication.class, args);

        String[] lista = applicationContext.getBeanDefinitionNames();
        System.out.println("====== Beans Registrados =====");
        //for(String bean : lista){
        //    System.out.println(""+bean);
        //}
        System.out.println("====== FIN Beans Registrados =====");
    }

}
