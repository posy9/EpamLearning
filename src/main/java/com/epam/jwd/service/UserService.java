package com.epam.jwd.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.epam.jwd.dao.UserDao;
import com.epam.jwd.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class UserService {

    private static final Logger LOG = LogManager.getLogger(UserService.class);
    private final UserDao userDao = UserDao.getInstance();

    public Optional<User> createUser(User user) throws InterruptedException {
        if(userDao.readUserByLogin(user.getLogin()).isPresent()) {
            return Optional.empty();
        }
        else {
            user = userWithHashedPassword(user);
            return userDao.create(user);
        }
    }

    public Optional<User> authenticate(String login, String password) throws InterruptedException {
        LOG.trace("authenticating user");
        if (login == null || password == null) {
            return Optional.empty();
        }
        final byte[] enteredPassword = password.getBytes(StandardCharsets.UTF_8);
        final Optional<User> readUser = userDao.readUserByLogin(login);
        final byte[] hashedPassword = readUser.get()
                    .getPassword()
                    .getBytes(StandardCharsets.UTF_8);
        return BCrypt.verifyer().verify(enteredPassword, hashedPassword).verified
                    ? readUser
                    : Optional.empty();

    }

    private User userWithHashedPassword(User user) {
       final char[] rawPassword = user.getPassword().toCharArray();
       String hashPassword = BCrypt.withDefaults().hashToString(12, rawPassword);
       return new User(user.getLogin(), hashPassword);
   }
}
