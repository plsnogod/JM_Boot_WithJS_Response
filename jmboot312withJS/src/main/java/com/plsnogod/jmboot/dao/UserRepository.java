package com.plsnogod.jmboot.dao;

import com.plsnogod.jmboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User getUserByName(String name);
}
