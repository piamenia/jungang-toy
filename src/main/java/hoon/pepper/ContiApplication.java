package hoon.pepper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"hoon.pepper"})
public class ContiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ContiApplication.class, args);
	}

}
