package dio.springboot.security.dio.init;

import dio.springboot.security.dio.model.User;
import dio.springboot.security.dio.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findByUsername("admin");
        if (user == null){
            user = new User();
            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword("{noop}123");
            user.getRoles().addAll(List.of("MANAGERS", "USERS"));
            userRepository.save(user);
        }
        System.out.println(user);
        user = userRepository.findByUsername("user");
        if(user ==null){
            user = new User();
            user.setName("USER");
            user.setUsername("user");
            user.setPassword("{noop}user123");
            user.getRoles().add("USERS");
            userRepository.save(user);
        }
        System.out.println(user);
    }
}
