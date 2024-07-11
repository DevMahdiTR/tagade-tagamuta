package tagarde;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tagarde.core.domain.role.Role;
import tagarde.core.repository.RoleRepository;
import tagarde.core.service.role.RoleService;

@SpringBootApplication
//@RequiredArgsConstructor
public class TagardeApplication {

	//private final RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(TagardeApplication.class, args);
	}

	/*@PostConstruct
	public void init() {
		roleRepository.save(new Role("ROLE_ADMIN"));
		roleRepository.save(new Role("ROLE_DOCTOR"));
		roleRepository.save(new Role("ROLE_GENERAL_MANAGER"));
		roleRepository.save(new Role("ROLE_DEPARTMENT_MANAGER"));
		roleRepository.save(new Role("ROLE_HOSPITAL_OWNER"));

	}*/

}
