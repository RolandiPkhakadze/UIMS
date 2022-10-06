package com.example.uims.service;

import com.example.uims.entity.User;
import com.example.uims.exception.AlreadyExistsException;
import com.example.uims.exception.NotFoundException;
import com.example.uims.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Iterable<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> createUser(final User user) {
        if (repository.existsByPersonalNo(user.getPersonalNo())) {
            return Optional.empty();
        }
        return Optional.of(repository.save(user));
    }

    public Optional<User> getUserByPersonalNo(final String personalNo) {
        return repository.findByPersonalNo(personalNo);
    }

    public User updateUser(final long id, final User user) {
        Optional<User> userOptional = repository.findByPersonalNo(user.getPersonalNo());
        if (userOptional.isPresent() && existsOtherUserWithPersonalNo(id, user, userOptional.get())) {
            String exceptionMessage = String.format("User with personal no = %s already exists", user.getPersonalNo());
            throw new AlreadyExistsException(exceptionMessage);
        }
        user.setId(id);
        return repository.save(user);
    }

    private boolean existsOtherUserWithPersonalNo(long id, User updatingUser, User existingUser) {
        return id != existingUser.getId() &&
                Objects.equals(updatingUser.getPersonalNo(), existingUser.getPersonalNo());
    }

    public User deleteUserById(final long id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isEmpty()) {
            String exceptionMessage = String.format("User with id = %s no found", id);
            throw new NotFoundException(exceptionMessage);
        }
        repository.deleteById(id);
        return userOptional.get();
    }
}
