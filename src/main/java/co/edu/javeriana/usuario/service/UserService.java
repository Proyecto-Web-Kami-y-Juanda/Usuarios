package co.edu.javeriana.usuario.service;

import co.edu.javeriana.usuario.entity.User;
import co.edu.javeriana.usuario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listAll(){
        return this.repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(String username){
        return repo.findById(username).get();
    }

    public void delete(String username) {
        repo.deleteById(username);
    }

    public void saveOrUpdate(User user)
    {
        repo.save(user);
    }

    public ResponseEntity<User> update (User user, String username) throws Exception {
        User nuevo = repo.findById(username).orElseThrow(() -> new Exception("No existe un usuario con ese id"));
        nuevo.setUserName(user.getUserName());
        nuevo.setPassword(user.getPassword());

        repo.save(nuevo);

        return ResponseEntity.ok (nuevo) ;
    }

}

