package com.straltsou.rest.ws.restfulwebservices.repository;

import com.straltsou.rest.ws.restfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}
