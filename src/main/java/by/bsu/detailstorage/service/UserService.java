package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.User;
import by.bsu.detailstorage.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static by.bsu.detailstorage.registry.EntityNameRegistry.USER;

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



}
