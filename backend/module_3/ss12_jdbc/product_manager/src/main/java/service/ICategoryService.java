package service;

import dto.CategoryDTO;
import entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getCategories();

    CategoryDTO getCategoryWithProducts(int id);

    boolean save(Category product);

    boolean update(Category product, int id);

    boolean remove(int id);

    Category viewCategory(int id);
}
