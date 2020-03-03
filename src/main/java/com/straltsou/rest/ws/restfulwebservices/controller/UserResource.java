package com.straltsou.rest.ws.restfulwebservices.controller;

import com.straltsou.rest.ws.restfulwebservices.entity.Post;
import com.straltsou.rest.ws.restfulwebservices.entity.User;
import com.straltsou.rest.ws.restfulwebservices.exception.UserNotFoundException;
import com.straltsou.rest.ws.restfulwebservices.repository.PostRepository;
import com.straltsou.rest.ws.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/user/{id}")
    public Resource<User> findOne(@PathVariable int id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("User not found, id=%s", id));
        }

        Resource<User> model = new Resource<>(user.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
        model.add(linkTo.withRel("all-users"));

        return model;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        // HTTP `create` best practices
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/user/{id}/posts")
    public List<Post> retrieveAllUsers(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        return userOptional.get().getPosts();
    }


    @PostMapping("/user/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        User user = userOptional.get();
        post.setUser(user);

        postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

}
