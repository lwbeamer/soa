package itmo.corp.secondaryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SecondaryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondaryServiceApplication.class, args);
    }

}
