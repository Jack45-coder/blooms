package in.codingage.blooms.repository.impl;

import in.codingage.blooms.models.User;
import in.codingage.blooms.repository.UserCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<User> findByEmail(String email){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        User user = mongoTemplate.findOne(query, User.class);
        return Optional.ofNullable(user);
    }

    public List<User> findUsersWithAgeAbove(Integer age){
        Query query = new Query();
        query.addCriteria(Criteria.where("age").gt(age));

        return mongoTemplate.find(query, User.class);
    }

    public List<User> findUsersWithAgeAboveUsingCB(Integer age, String role){
        Query query = new Query();
        if(age != null){
            query.addCriteria(Criteria.where("age").gt(age));
        }

        if(role != null){
            query.addCriteria(Criteria.where("role").is(role));
        }

        return  mongoTemplate.find(query, User.class);
    }
}
