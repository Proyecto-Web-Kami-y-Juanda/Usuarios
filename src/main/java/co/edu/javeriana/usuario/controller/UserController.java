package co.edu.javeriana.usuario.controller;

import co.edu.javeriana.usuario.entity.User;
import co.edu.javeriana.usuario.service.UserService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private final UserService userService;
    public UserController(UserService bookService)
    {
        this.userService = bookService;
    }

    @RequestMapping("/")
    public String findAllUsers(Model model){
        List<User> users = this.userService.listAll();
        for (int i = 0; i< users.size(); i++){
            System.out.println(users.get(i).getUserName());
        }
        model.addAttribute("user", users);
        return "done";
    }

    @GetMapping("/")
    public  List<User> list(){
        return userService.listAll();
    }

    @DeleteMapping("/users/{userid}")
    private void deleteBook(@PathVariable("userid") String userid){
        userService.delete(userid);
    }

    @PostMapping("/users")
    private String  saveUser(@RequestBody User users){
        userService.saveOrUpdate(users);
        return users.getUserName();
    }

    @PutMapping("/users/{userid}")
    private ResponseEntity<?> update(@RequestBody User user, @PathVariable("userid") String userid) throws Exception{
        return userService.update(user, userid);
    }
}
