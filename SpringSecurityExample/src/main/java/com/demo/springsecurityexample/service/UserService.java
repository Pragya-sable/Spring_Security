package com.demo.springsecurityexample.service;

import com.demo.springsecurityexample.model.Users;
import com.demo.springsecurityexample.repository.UserRepo;

import org. springframework. security. core. Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    // here we use bcrypt library
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12) ;

    public Users register(Users users){
        users.setPassword(encoder.encode(users.getPassword()));
      return  repo.save(users);
    }

    public String verify(Users users) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
       if(authentication.isAuthenticated())
          // return "Success";
           return jwtService.generateToken(users.getUsername());
       return "fail";


    }
}
