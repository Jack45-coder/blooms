package in.codingage.blooms.repository;

import in.codingage.blooms.models.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends MongoRepository<SubCategory, String > {
    List<SubCategory> findAllByCategoryIdAndActiveTrue(String categoryId);

    List<SubCategory> findAllByActiveTrue();

    Optional<SubCategory> findByIdAndActiveTrue(String subCategoryId);

    Optional<SubCategory> findByNameAndActiveTrue(String name);
}
