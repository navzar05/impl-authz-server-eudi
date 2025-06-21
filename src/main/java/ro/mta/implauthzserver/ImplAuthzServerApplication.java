package ro.mta.implauthzserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.mta.implauthzserver.entity.User;
import ro.mta.implauthzserver.repository.UserRepository;

import java.util.Set;

@SpringBootApplication(scanBasePackages = {
        "ro.mta.implauthzserver",
        "ro.mta.baseauthzserver"
})
@EntityScan(basePackages = {
        "ro.mta.implauthzserver.entity",
        "ro.mta.baseauthzserver.entity"
})
@EnableJpaRepositories(basePackages = {
        "ro.mta.implauthzserver.repository",
        "ro.mta.baseauthzserver.repository"
})
public class ImplAuthzServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImplAuthzServerApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Create sample users
            User student = User.builder()
                    .username("student1")
                    .password(passwordEncoder.encode("password"))
                    .firstName("John")
                    .lastName("Doe")
                    .email("john.doe@mta.ro")
                    .studentId("STU2024001")
                    .university("Military Technical Academy")
                    .graduationYear("2024")
                    .isStudent(true)
                    .roles(Set.of("USER", "STUDENT"))
                    .enabled(true)
                    .vct("urn:org:certsign:university:graduation:1")
                    .build();

            User graduate = User.builder()
                    .username("graduate1")
                    .password(passwordEncoder.encode("password"))
                    .firstName("Jane")
                    .lastName("Smith")
                    .email("jane.smith@alumni.mta.ro")
                    .studentId("STU2020001")
                    .university("Military Technical Academy")
                    .graduationYear("2023")
                    .isStudent(false)
                    .roles(Set.of("USER", "GRADUATE"))
                    .enabled(true)
                    .vct("urn:org:certsign:university:graduation:1")
                    .build();

            userRepository.save(student);
            userRepository.save(graduate);

            System.out.println("Sample users created:");
            System.out.println("- Username: student1, Password: password (Current student)");
            System.out.println("- Username: graduate1, Password: password (Graduate)");
            System.out.println("\nAuthorization Server is running at https://localhost:9000");
            System.out.println("OpenID Configuration: https://localhost:9000/.well-known/openid-configuration");
        };
    }
}
