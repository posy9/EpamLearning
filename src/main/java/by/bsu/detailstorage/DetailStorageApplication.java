package by.bsu.detailstorage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "by.bsu.detailstorage.model")
public class DetailStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(DetailStorageApplication.class, args);
    }
}
