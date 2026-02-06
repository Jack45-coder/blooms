package in.codingage.blooms.repository;

import in.codingage.blooms.models.User;

import java.util.List;

public interface UserCustomRepository {
    public User findByEmail(String email);

    List<User> findUsersWithAgeAbove(Integer age);
}
