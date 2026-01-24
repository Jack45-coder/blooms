package in.codingage.blooms.repository;

import in.codingage.blooms.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
    List<Blog> findByAuthorIdAndActiveTrue(String authorId);
    List<Blog> findByActiveTrue();

    Optional<Blog> findByIdAndActiveTrue(String blogId);
}
