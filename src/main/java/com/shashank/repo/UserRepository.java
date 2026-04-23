package com.shashank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shashank.models.User;

public interface UserRepository extends JpaRepository<User, String> {

}