package com.easypatient.easypatient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Easy Patient",
        version = "0.0.1",
        description = "swagger api for Easy Patient application"))
public class EasyPatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyPatientApplication.class, args);
    }

}
