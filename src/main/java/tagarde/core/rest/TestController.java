package tagarde.core.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tagarde.core.utility.CustomerResponse;

@RestController
@RequestMapping("api/v1/test")
public class TestController {



    @GetMapping("/tt")
    public ResponseEntity<Object> test()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        return new CustomerResponse<>(userDetails, HttpStatus.ACCEPTED);
    }
}
