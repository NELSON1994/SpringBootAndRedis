package com.redis.springboot.redis.controller;

import com.redis.springboot.redis.model.User;
import com.redis.springboot.redis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableCaching
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save")
    public User save(@RequestBody User user1){
        return userRepository.save(user1);

    }

    @GetMapping("/getAll")
    //@Cacheable()
    public List<User> getAll(){
        return userRepository.findAll();

    }

    @GetMapping("/user/{id}")
   // @Cacheable(key = "#id",value = "User")
    @Cacheable(key = "#id",value = "User", unless = "#result.department==IT")// BASED ON CONDITION
    public User getUser(@PathVariable int id){
        return userRepository.findById(id);

    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id){
        return userRepository.deleteUser(id);

    }
}
