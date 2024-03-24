package istad.co.practicespringmvc.service;

import istad.co.practicespringmvc.dto.CategoryRequest;
import istad.co.practicespringmvc.dto.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    List<CategoryResponse> getAllCategory(String categoryName);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse findCategoryByID (int id);
    void deleteCategory(int categoryId);
    CategoryResponse updateCategory(int id, CategoryRequest categoryRequest);
}
