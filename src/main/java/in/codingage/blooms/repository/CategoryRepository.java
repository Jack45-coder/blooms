package in.codingage.blooms.repository;

import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    // Custom query method to find all active categories.
    List<Category> findAllByActiveTrue();

    Optional<Category> findByName(String name);

    Optional<Category> findByNameAndActiveTrue(String name);

    List<Category> findAllByStatus(String displayName);

    Optional<Category> findByIdAndActiveTrue(String id);

    List<Category> findAllByStatusAndActiveTrue(Status displayName);
}
