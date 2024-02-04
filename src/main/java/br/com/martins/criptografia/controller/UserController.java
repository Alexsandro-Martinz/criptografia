package br.com.martins.criptografia.controller;

import br.com.martins.criptografia.entity.UserEntity;
import br.com.martins.criptografia.exception.UserNotFoundException;
import br.com.martins.criptografia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserEntity> getListUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    public UserEntity createNewUser(@RequestBody UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @PutMapping("/users/{id}")
    public UserEntity updateUser(@RequestBody UserEntity userEntity, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserDocument(userEntity.getUserDocument());
                    user.setCreditCardToken(userEntity.getCreditCardToken());
                    user.setValue(userEntity.getValue());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
