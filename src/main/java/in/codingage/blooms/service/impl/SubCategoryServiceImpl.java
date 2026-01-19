package in.codingage.blooms.service.impl;

import in.codingage.blooms.dto.SubCategoryRequest;
import in.codingage.blooms.models.Status;
import in.codingage.blooms.models.SubCategory;
import in.codingage.blooms.repository.SubCategoryRepository;
import in.codingage.blooms.service.SubCategoryService;
import in.codingage.blooms.utlils.RandomIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public void createSubCategory(SubCategoryRequest subCategoryRequest) {
        // Implementation for creating a SubCategory
        if (subCategoryRequest == null) throw new RuntimeException("Request null not required!");

        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequest.getName());
        subCategory.setDescription(subCategoryRequest.getDescription());
        subCategory.setCategoryId(subCategoryRequest.getCategoryId());
        subCategory.setId(RandomIdUtils.generateRandom(6));
        subCategory.setCreatedDTTM(LocalDateTime.now());
        subCategory.setCreatedBy("ADMIN");
        subCategory.setStatus(Status.PUBLISHED.getDisplayName());
        subCategory.setActive(true);

        // save in db
        subCategoryRepository.save(subCategory);
    }
}
