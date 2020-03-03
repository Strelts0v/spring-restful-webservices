package com.straltsou.rest.ws.restfulwebservices.repository;

import com.straltsou.rest.ws.restfulwebservices.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
