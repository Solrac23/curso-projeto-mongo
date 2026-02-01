package com.example.workshopmongo.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.AuthorDTO;
import com.example.workshopmongo.dto.CommentDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
      DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      fmt.withZone(ZoneId.of("GMT"));

      userRepository.deleteAll();
      postRepository.deleteAll();

      User maria = new User(null, "Maria Brown", "maria@gmail.com");
      User alex = new User(null, "Alex Green", "alex@gmail.com");
      User bob = new User(null, "Bob Grey", "bob@gmail.com");

      userRepository.saveAll(Arrays.asList(maria, alex, bob));

      Post post1 = new Post(
        null,
        fmt.parse("21/03/2026", LocalDate::from),
        "Partiu viagem",
        "Vou viajar para São Paulo. Abraços!",
        new AuthorDTO(maria)
      );

      Post post2 = new Post(
        null,
        fmt.parse("23/03/2026", LocalDate::from),
        "Bom dia",
        "Acordei feliz hoje!",
        new AuthorDTO(maria)
      );

      CommentDTO c1 = new CommentDTO(
        "Boa viagem mano!",
        fmt.parse("21/03/2026", LocalDate::from),
        new AuthorDTO(alex)
      );

      CommentDTO c2 = new CommentDTO(
        "Aproveite bem!",
        fmt.parse("21/03/2026", LocalDate::from),
        new AuthorDTO(bob)
      );

      CommentDTO c3 = new CommentDTO(
        "Tenha um ótimo dia!",
        fmt.parse("23/03/2026", LocalDate::from),
        new AuthorDTO(alex)
      );

      post1.getComments().addAll(Arrays.asList(c1, c2));
      post2.getComments().addAll(Arrays.asList(c3));
      
      postRepository.saveAll(Arrays.asList(post1, post2));

      maria.getPosts().addAll(Arrays.asList(post1, post2));
      userRepository.save(maria);
    }

}