package com.example.workshopmongo.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.resources.util.URL;
import com.example.workshopmongo.services.PostService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
  
  @Autowired
  private PostService service;

  @GetMapping("/{id}")
  public ResponseEntity<Post> findById(@PathVariable String id) {
    Post obj = service.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping("/title-search")
  public ResponseEntity<List<Post>> findByTitle(
    @RequestParam(value = "text", defaultValue = "") String text
  ) {
    text = URL.decodeParam(text);
    List<Post> obj = service.findByTitle(text);
    return ResponseEntity.ok().body(obj);
  }

   @GetMapping("/full-search")
  public ResponseEntity<List<Post>> fullSearch(
    @RequestParam(value = "text", defaultValue = "") String text,
    @RequestParam(value = "minDate", defaultValue = "") String minDate,
    @RequestParam(value = "maxDate", defaultValue = "") String maxDate
  ) {
    text = URL.decodeParam(text);
    LocalDate min = URL.convertDate(minDate, LocalDate.of(1970, 1, 1));
    LocalDate max = URL.convertDate(maxDate, LocalDate.now());
    List<Post> obj = service.fullSearch(text, min, max);
    return ResponseEntity.ok().body(obj);
  }
}
