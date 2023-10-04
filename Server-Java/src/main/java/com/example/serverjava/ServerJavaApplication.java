package com.example.serverjava;

import com.example.serverjava.Config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class ServerJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerJavaApplication.class, args);
	}

}
