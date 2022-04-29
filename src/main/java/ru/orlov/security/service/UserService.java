package ru.orlov.security.service;



import ru.orlov.security.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User newUser);

    User getByIdUser(Long id);

    User findByEmail(String email);

}
