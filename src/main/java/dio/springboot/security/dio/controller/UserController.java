package dio.springboot.security.dio.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.attribute.UserPrincipalLookupService;

@RestController
@RequestMapping
public class UserController {

    @GetMapping
    public String welcome() {
        return "Welcome to Spring Boot";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public String user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return "Authorized " + currentPrincipalName;
    }

    @GetMapping("/managers")
    @PreAuthorize("hasRole('MANAGERS')")
    public String managers() {
        return "Authorized managers";
    }
}
