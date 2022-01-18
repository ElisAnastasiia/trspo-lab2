package com.onlineshop.serviceidentity.api;


import com.onlineshop.serviceidentity.repo.model.Identity;
import com.onlineshop.serviceidentity.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class IdentityController {
    private final IdentityService identityService;

    // getAllUsers
    @GetMapping
    public ResponseEntity<List<Identity>> index() {
        final List<Identity> users = identityService.fetchAll();
        return ResponseEntity.ok(users);
    }

    // getUserById
    @GetMapping("/{id}")
    public ResponseEntity<Identity> getById(@PathVariable long id) {
        try {
            Identity user = identityService.getUserById(id);
            return ResponseEntity.ok(user);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Create User
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.onlineshop.serviceidentity.api.dto.Identity user){
        final String username = user.getUsername();
        final String firstname = user.getFirstname();
        final String lastname = user.getLastname();
        final String email = user.getEmail();
        final String password = user.getPassword();
        final long id = identityService.createUser(username, firstname, lastname, email, password);
        final String location = String.format("/users/" + id);

        return ResponseEntity.created(URI.create(location)).build();
        // 201 status code created() URI location - location resours'a kotoriy sozdadim
    }

    // Update user info
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.onlineshop.serviceidentity.api.dto.Identity user){
        final String username = user.getUsername();
        final String firstname = user.getFirstname();
        final String lastname = user.getLastname();
        final String email = user.getEmail();
        final String password = user.getPassword();

        try {
            identityService.updateUser(id,username, firstname, lastname, email, password);
            return ResponseEntity.noContent().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();

        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        identityService.deleteUser(id);
        return  ResponseEntity.noContent().build();
    }


}

