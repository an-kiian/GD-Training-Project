package store;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import store.model.Product;
import store.repository.ProductRepository;

@SpringBootApplication
@EntityScan("store.model")
@EnableJpaRepositories("store.repository")
public class Application {
    @Autowired
    private ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
           productRepository.save(new Product(67,"Pants","Good Pants"));
            productRepository.save(new Product(31,"Blouse","Bad Blouse"));
        };
    }}
