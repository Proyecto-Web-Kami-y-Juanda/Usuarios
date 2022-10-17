package co.edu.javeriana.usuario.repository;

import co.edu.javeriana.usuario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
