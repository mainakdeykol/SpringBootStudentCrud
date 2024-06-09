package com.aegon.SpringBootStudentCrud.Config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("org.aegon.SpringBootStudentCrud")
public class AppConfig {

    @Bean
    @Scope(value = "prototype")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
