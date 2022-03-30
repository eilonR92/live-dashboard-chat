package com.example.LiveUsersDashboard.repo;

import com.example.LiveUsersDashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

      void deleteUserById(Long id);

      Optional<User> findUserById(Long id);

      Optional<User> findByEmail(String email);

      Optional<User> findUserByUserName(String userName);

}

