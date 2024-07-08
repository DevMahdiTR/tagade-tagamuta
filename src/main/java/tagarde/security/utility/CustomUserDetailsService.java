package tagarde.security.utility;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tagarde.core.exceptions.custom.ResourceNotFoundException;
import tagarde.core.repository.UserEntityRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    @Autowired
    public CustomUserDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userEntityRepository.fetchUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Bad credentials"));
    }
}
