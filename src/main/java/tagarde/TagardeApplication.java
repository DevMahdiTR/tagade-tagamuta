package tagarde;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tagarde.core.domain.role.Role;
import tagarde.core.repository.RoleRepository;
import tagarde.core.service.role.RoleService;

@SpringBootApplication
public class TagardeApplication {
	public static void main(String[] args) {
		SpringApplication.run(TagardeApplication.class, args);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "Password@123";
		String hashedPassword = encoder.encode(rawPassword);
		System.out.println("Hashed Password: " + hashedPassword);
	}

}
