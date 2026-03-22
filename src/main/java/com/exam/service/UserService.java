package com.exam.service;

import com.exam.model.dto.UserLoginDTO;
import com.exam.model.dto.UserRegisterDTO;
import com.exam.model.entity.User;
import com.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
static class UserService {

}
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setEmail(userRegisterDTO.getEmail());
        user.setFullName(userRegisterDTO.getFullName());
        return userRepository.save(user);
    }

    public ResponseEntity<?> login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername()).orElse(null);
        if (user != null && passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(user);
        }else {
            return new ResponseEntity<>("Username or password is incorrect", HttpStatus.BAD_REQUEST);
        }
    }
}
