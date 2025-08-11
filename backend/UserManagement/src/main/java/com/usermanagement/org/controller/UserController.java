package com.usermanagement.org.controller;

import com.usermanagement.org.exception.UserNotFoundException;
import com.usermanagement.org.model.User;
import com.usermanagement.org.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    public User editUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepository.findById(id)
                .map(user->{
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));

    }
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id : "+id+" has been deleted successfully";
    }

}
