import Repositories.interfaces.ProductRepository;
import model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(ProductRepository repository) {
//        return new CommandLineRunner() {
//            // начиная со spring boot 1.5 ридонли над методом который вызывает репозиторий нужен обязательно
//            // что бы не закрывалась сессия.
//            @Override
//            @Transactional(readOnly = true)
//            public void run(String... args) throws Exception {
//
//                System.out.println("\findById");
////                for (Product product : repository.findById(2)) {
////                    System.out.println(product);
////                }
//
////                System.out.println("\n2.findByEmail(String email)...");
////                for (User user : repository.findByEmail("111@leodev.ru")) {
////                    System.out.println(user);
////                }
////
////                System.out.println("\n3.findByDate(Date date)...");
////                for (User user : repository.findByDate(sdf.parse("2018-03-22"))) {
////                    System.out.println(user);
////                }
////
////                // For Stream, need @Transactional
////                System.out.println("\n4.findByEmailReturnStream(@Param(\"email\") String email)...");
////                try (Stream&lt;User&gt; stream = repository.findByEmailReturnStream("333@leodev.ru")) {
////                    stream.forEach(System.out::println);
////                }
//
//                System.out.println("Done!");
//            }
//        };
}
