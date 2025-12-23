package demo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "demo")
public class WebEditionApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebEditionApplication.class, args);
    }
}
