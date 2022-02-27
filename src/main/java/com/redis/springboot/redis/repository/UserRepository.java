package com.redis.springboot.redis.repository;

import com.redis.springboot.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private RedisTemplate redisTemplate;

    public User save(User user) {
        redisTemplate.opsForHash().put("User", user.getUserId(), user);
        return user;
    }

    public List<User> findAll() {
        return redisTemplate.opsForHash().values("User");
    }

    public User findById(int id) {
        System.out.println("called from db");
        return (User) redisTemplate.opsForHash().get("User", id);
    }

    public String deleteUser(int id) {
        redisTemplate.opsForHash().delete("User", id);
        return "User deleted Successfully";
    }


}
