package com.example.LiveUsersDashboard.server;

import com.example.LiveUsersDashboard.exceptions.UserNotFoundException;
import com.example.LiveUsersDashboard.model.User;
import com.example.LiveUsersDashboard.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;


    @Autowired
    public UserService(UserRepo userrepo) {
        this.userRepo = userrepo;
    }

    public User addUser(User user){
        return userRepo.save(user);
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public User updateUser(User user, boolean login){
        int currLogin = user.getLogsCount();
        if (login){
            user.setLogsCount(currLogin + 1);
            user.setLastLogIn(Instant.now());
            user.setOnline(true);
        }
        else{
            user.setOnline(false);
        }
        return userRepo.save(user);
    }

    public void deleteUser(Long id){
        userRepo.deleteUserById(id);
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id" + id + "was not found"));
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Invalid username or password"));
    }

    public User findByUserName(String userName) {
        return userRepo.findUserByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }


}
