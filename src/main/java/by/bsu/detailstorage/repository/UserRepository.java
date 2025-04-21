package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.User;

import java.util.Optional;

public interface UserRepository extends EntityRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
