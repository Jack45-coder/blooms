package in.codingage.blooms.repository;

import in.codingage.blooms.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
    List<Blog> findByAuthorIdAndActiveTrue(String authorId);
    List<Blog> findByIsActiveTrue();
}
