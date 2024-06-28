package dio.springboot.security.dio.repository;

import dio.springboot.security.dio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u join fetch u.roles where u.username = (:username) ")
    User findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);

}
