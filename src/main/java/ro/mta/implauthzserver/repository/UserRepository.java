package ro.mta.implauthzserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.mta.baseauthzserver.repository.BaseUserRepository;
import ro.mta.implauthzserver.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseUserRepository<User> {
    Optional<User> findByUsername(String username);
}