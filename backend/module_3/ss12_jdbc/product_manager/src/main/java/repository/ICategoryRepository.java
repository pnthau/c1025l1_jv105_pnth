package repository;

import dto.CategoryDTO;
import entity.Category;
import entity.Product;

import java.util.List;

public interface ICategoryRepository {
    List<Category> getCategories();

    CategoryDTO getCategoryWithProducts(int id);

    boolean save(Category product);

    boolean update(Category product, int id);

    boolean remove(int id);

    Category viewCategory(int id);

}
