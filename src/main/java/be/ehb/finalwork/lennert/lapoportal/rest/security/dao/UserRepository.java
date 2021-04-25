package be.ehb.finalwork.lennert.lapoportal.rest.security.dao;

import be.ehb.finalwork.lennert.lapoportal.rest.security.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
