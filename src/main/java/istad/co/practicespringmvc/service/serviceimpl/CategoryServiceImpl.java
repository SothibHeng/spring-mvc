package istad.co.practicespringmvc.service.serviceimpl;
import istad.co.practicespringmvc.dto.CategoryRequest;
import istad.co.practicespringmvc.dto.CategoryResponse;
import istad.co.practicespringmvc.model.Category;
import istad.co.practicespringmvc.repository.CategoryRepository;
import istad.co.practicespringmvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private Category searchCategoryByID(int id){
        return  categoryRepository.getAllCategory()
                .stream().filter(p->p.getId()==id)
                .findFirst()
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND,"Category doesn't exist!!"));
    }

    private CategoryResponse mapCategoryToResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    private Category mapRequestToCategory(CategoryRequest request){
        return Category.builder()
                .title(request.title())
                .description(request.description())
                .build();
    }

    // method implements

    @Override
    public List<CategoryResponse> getAllCategory(String categoryName) {
        var category = categoryRepository.getAllCategory();
        if (!categoryName.isEmpty()){
            category = category.stream().filter(
                    cate-> cate.getTitle().toLowerCase().contains(categoryName.toLowerCase())
            ).toList();
        }
        return  category
                .stream()
                .map(cate->{
                    return CategoryResponse.builder()
                            .id(cate.getId())
                            .title(cate.getTitle())
                            .description(cate.getDescription())
                            .build();
                }).toList();

    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category newCategory = mapRequestToCategory(request);
        var maxID = categoryRepository.getAllCategory()
                .stream()
                .max(Comparator.comparingInt(Category::getId))
                .map(Category::getId);
        int newID=1;
        if(maxID.isPresent()) {
            newID = maxID.get() + 1;
        }
        newCategory.setId(newID);
        categoryRepository.addCategory(newCategory);

        return mapCategoryToResponse(newCategory);
    }

    @Override
    public CategoryResponse findCategoryByID(int id) {
        return mapCategoryToResponse(searchCategoryByID(id));

    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteCategory(searchCategoryByID(categoryId).getId());
    }

    @Override
    public CategoryResponse updateCategory(int id, CategoryRequest categoryRequest) {
        var result = searchCategoryByID(id);
        result= mapRequestToCategory(categoryRequest);
        result.setId(id);
        categoryRepository.updateCategory(result);
        return mapCategoryToResponse(result);
    }
}
