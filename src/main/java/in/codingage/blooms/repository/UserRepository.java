package in.codingage.blooms.repository;

import in.codingage.blooms.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

    Optional<User> findByPhoneAndPassword(String phone, String password);
}
