package config;

import model.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import service.UserService;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDefaultUsers(UserService userService, PasswordEncoder passwordEncoder) {
        return args -> {

            // ✅ Create Admin if not exists
            if (userService.findByUsername("admin").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setName("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles("ROLE_ADMIN");
                userService.save(admin);
                System.out.println("✅ Default admin created (username=admin, password=admin123)");
            }

            // ✅ Create Employee if not exists
            if (userService.findByUsername("employee").isEmpty()) {
                AppUser employee = new AppUser();
                employee.setName("employee");
                employee.setPassword(passwordEncoder.encode("emp123"));
                employee.setRoles("ROLE_EMPLOYEE");
                userService.save(employee);
                System.out.println("✅ Default employee created (username=employee, password=emp123)");
            }
        };
    }
}
