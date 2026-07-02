package service;

import dto.CategoryDTO;
import entity.Category;
import repository.CategoryRepository;
import repository.ICategoryRepository;

import java.util.List;

public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository = new CategoryRepository();

    @Override
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    @Override
    public CategoryDTO getCategoryWithProducts(int id) {
        return categoryRepository.getCategoryWithProducts(id);
    }

    @Override
    public boolean save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean update(Category category, int id) {
        return categoryRepository.update(category, id);
    }

    @Override
    public boolean remove(int id) {
        return categoryRepository.remove(id);
    }

    @Override
    public Category viewCategory(int id) {
        return categoryRepository.viewCategory(id);
    }
}
