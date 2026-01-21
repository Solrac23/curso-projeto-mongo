package com.example.workshopmongo.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongo.domain.User;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  @GetMapping("")
  public ResponseEntity<List<User>> findAll() {
    User maria = new User("1", "Maria Brown", "maria@gmail.com");
    User alex = new User("2", "Alex Green", "alex@gmail.com");
    List<User> users = List.of(maria, alex);
    return ResponseEntity.ok().body(users);
  }
  
}
