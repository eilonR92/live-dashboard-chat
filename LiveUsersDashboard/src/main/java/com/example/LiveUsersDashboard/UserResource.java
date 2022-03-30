package com.example.LiveUsersDashboard;

import com.example.LiveUsersDashboard.model.User;
import com.example.LiveUsersDashboard.server.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user, HttpServletRequest httpServletRequest){
        try{
            User u = userService.findByEmail(user.getEmail());
            if (!user.getPassword().equals(u.getPassword())){
                throw new RuntimeException("Incorrect Password");
            }
            String ipAddr = httpServletRequest.getRemoteAddr();
            u.setIp(ipAddr);
            userService.updateUser(u, true);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        catch (Exception err){
            System.out.println(err);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user,  HttpServletRequest httpServletRequest) {
        try{
            if (isUserExists(user.getEmail())){
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            String ipAddr = httpServletRequest.getRemoteAddr();
            user.setIp(ipAddr);
            user.setOnline(true);
            User newUser = userService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        catch (Exception err){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public boolean isUserExists(String email){
        try{
            User u = userService.findByEmail(email);
            return true;
        }
        catch (RuntimeException err){
            return false;
        }
    }


    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody String userName){
        User user = userService.findByUserName(userName);
        user.setOnline(false);
        userService.updateUser(user, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

