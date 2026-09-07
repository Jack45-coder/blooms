package in.codingage.blooms.repository;

import in.codingage.blooms.models.Role;
import in.codingage.blooms.models.User;
import org.springdoc.core.converters.models.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findFirstByUserName(String userName);

    Optional<User> findByUserNameAndPhone(String userName, String phone);
    Optional<User> findByPhone(String phone);
    Optional<User> findByPassword(String password);

    List<User> findByRole(Role role);

    List<User> findByRoleOrderByAgeDesc(Role role);

    List<User> findByRole(Role role, Sort sort);

    Long countByRole(Role role);

    List<User> findByRoleAndAgeGreaterThan(Role role, Integer age);
    List<User> findByRoleAndAgeLessThan(Role role, Integer age);
    List<User> findByAgeBetween(Role role, int minAge, int maxAge);
//    List<User> findByUserNameContaining(String str);
//    List<User> findByUserNameStartingWith(String str);
//    List<User> findByUserNameEndingWith(String str);
//    List<User> findByUserNameIgnoreCase(String str);


    Optional<User> findByUserName(String username);
}
