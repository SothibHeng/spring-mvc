package istad.co.practicespringmvc.repository;

import istad.co.practicespringmvc.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryRepository {
    private final List<Category> allCategory = new ArrayList<>(){{
        add(Category.builder()
                .id(1)
                .title("product title one ")
                .description("this is the product one description")
                .build());

        add(Category.builder()
                .id(2)
                .title("product title two ")
                .description("this is the product two description")
                .build());

        add(Category.builder()
                .id(4)
                .title("product title three ")
                .description("this is the product one description")
                .build());
    }};

    public List<Category> getAllCategory () {
        return allCategory;
    }
    public void addCategory(Category category){
        allCategory.add(category);
    }

    public void updateCategory (Category category) {
        int index = allCategory.indexOf(
                allCategory.stream()
                        .filter(cate -> cate.getId() == category.getId())
                        .findFirst()
                        .orElse(null)
        );
        allCategory.set(index, category);
    }

    public void deleteCategory(int id) {
        int index = allCategory.indexOf(
                allCategory.stream()
                        .filter(cate -> cate.getId() == id)
                        .findFirst()
                        .orElse(null)
        );
        allCategory.remove(index);
    }
}
