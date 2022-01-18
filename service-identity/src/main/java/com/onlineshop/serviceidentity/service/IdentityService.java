package com.onlineshop.serviceidentity.service;

import com.onlineshop.serviceidentity.repo.IdentityRepo;
import com.onlineshop.serviceidentity.repo.model.Identity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IdentityService {

    private final IdentityRepo userRepo;

    // All users from Database
    public List<Identity> fetchAll() { return userRepo.findAll(); }

    // Find users by id
    public Identity getUserById(long id) throws IllegalArgumentException {
        final Optional<Identity> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new IllegalArgumentException("Invalid user ID");
    }

    // Create user
    public long createUser(String username, String firstname, String lastname, String email, String password) {
        final Identity user = new Identity(username, firstname, lastname, email,password);
        final Identity savedUser = userRepo.save(user);
        return savedUser.getId();
    }

    // Update user info
    public void updateUser(Long id,String username, String firstname, String lastname, String email, String password)
            throws IllegalArgumentException {
        final Optional<Identity> optionalUser = userRepo.findById(id);

        if (optionalUser.isEmpty())
            throw new IllegalArgumentException("Invalid user ID");

        final Identity user = optionalUser.get();
        if (username != null && !username.isBlank()) user.setUsername(username);
        if (firstname != null && !firstname.isBlank()) user.setFirstname(firstname);
        if (lastname != null && !lastname.isBlank()) user.setLastname(lastname);
        if (email!= null && !email.isBlank()) user.setEmail(email);
        if (password != null && !password.isBlank()) user.setPassword(password);
        userRepo.save(user);
    }

    // Delete user
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }
}
