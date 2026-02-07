package in.codingage.blooms.repository;

import in.codingage.blooms.models.Role;
import in.codingage.blooms.models.User;
import org.springdoc.core.converters.models.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findFirstByName(String name);

    Optional<User> findByPhoneAndPassword(String phone, String password);

    List<User> findByRole(Role role);

    List<User> findByRoleOrderByAgeDesc(Role role);

    List<User> findByRole(Role role, Sort sort);

    Long countByRole(Role role);

    List<User> findByRoleAndAgeGreaterThan(Role role, Integer age);
    List<User> findByRoleAndAgeLessThan(Role role, Integer age);
    List<User> findByAgeBetween(Role role, int minAge, int maxAge);
    List<User> findByNameContaining(String str);
    List<User> findByNameStartingWith(String str);
    List<User> findByNameEndingWith(String str);
    List<User> findByNameIgnoreCase(String str);


}
