package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.User;
import by.bsu.detailstorage.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.USER;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.ENTITY_NOT_FOUND;

@Service
public class UserService extends AbstractService<User> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository, USER);
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User createEntity(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    @PreAuthorize("#id != authentication.principal.id")
    public void deleteEntity(long id) {
        super.deleteEntity(id);
    }
}
