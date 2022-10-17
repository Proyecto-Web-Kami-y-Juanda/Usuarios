package co.edu.javeriana.usuario.controller;

import co.edu.javeriana.usuario.entity.User;
import co.edu.javeriana.usuario.service.UserService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        model.addAttribute("user", users);
        return "done";
    }

    @GetMapping("/")
    public  List<User> list(){
        List<User> listFinal = new ArrayList<User>();
        for(int i = 0; i < userService.listAll().size(); i++){
            if(!userService.listAll().get(i).isInactivate()){
                listFinal.add(userService.listAll().get(i));
            }
        }
        return listFinal;
    }

    @DeleteMapping("/users/{userid}")
    private void deleteBook(@PathVariable("userid") String username){
        userService.delete(username);
    }

    @PostMapping("/users")
    private String  saveUser(@RequestBody User users){
        userService.saveOrUpdate(users);
        return users.getUserName();
    }

    @PutMapping("/users/{userid}")
    private ResponseEntity<?> update(@RequestBody User user, @PathVariable("userid") String username) throws Exception{
        return userService.update(user, username);
    }
}