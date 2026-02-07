package in.codingage.blooms.repository;

import in.codingage.blooms.models.User;

import java.util.List;
import java.util.Optional;

public interface UserCustomRepository {
    Optional<User> findByEmail(String email);

    List<User> findUsersWithAgeAbove(Integer age);

    List<User> findUsersWithAgeAboveUsingCB(Integer age, String role);
}
