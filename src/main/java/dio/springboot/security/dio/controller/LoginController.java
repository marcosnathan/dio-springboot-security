package dio.springboot.security.dio.controller;

import dio.springboot.security.dio.model.User;
import dio.springboot.security.dio.model.dto.LoginRequest;
import dio.springboot.security.dio.model.dto.Session;
import dio.springboot.security.dio.repository.UserRepository;
import dio.springboot.security.dio.security.JWTService;
import dio.springboot.security.dio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public Session login(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(loginRequest.getUsername()));

        boolean passwordOk =  passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!passwordOk) {
            throw new RuntimeException("Senha inválida para o login: " + loginRequest.getUsername());
        }
        Session session = new Session();
        session.setLogin(user.getUsername());
        session.setToken(jwtService.generateToken(user));
        return session;
    }
}
