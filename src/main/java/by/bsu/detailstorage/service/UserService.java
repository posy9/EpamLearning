package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.User;
import by.bsu.detailstorage.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static by.bsu.detailstorage.registry.EntityNameRegistry.BRAND;

@Service
public class UserService extends AbstractService<User> implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        super(repository, BRAND);
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
