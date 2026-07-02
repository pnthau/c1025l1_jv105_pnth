package repository;

import dto.CategoryDTO;
import entity.Category;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends BaseRepository implements ICategoryRepository {

    private static final String SELECT_ALL_CATEGORIES = "select * from categories";
    private static final String ADD_CATEGORY = "INSERT INTO categories(name) values(?)";
    private static final String UPDATE_CATEGORY = "update categories set name = ? where id = ?";
    private static final String REMOVE_CATEGORY = "delete from categories where id=?";
    private static final String SELECT_CATEGORY = "select * from categories where id =? ";
    private static final String SELECT_CATEGORY_PRODUCTS =
            "select p.id as product_id, p.name as product_name, p.price as product_price, c.name as category_name " +
                    "from categories c " +
                    "inner join products p on c.id = p.category_id " +
                    "where c.id = ?";

    @Override
    public List<Category> getCategories() {
        List<Category> list = new ArrayList<>();
        Category category = null;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                list.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public CategoryDTO getCategoryWithProducts(int id) {
        CategoryDTO categoryDTO = null;
        Product product = null;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_PRODUCTS);
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (categoryDTO == null) {
                        categoryDTO = new CategoryDTO();
                        categoryDTO.setName(resultSet.getString("category_name"));
                        categoryDTO.setProducts(new ArrayList<>());
                    }
                    product = new Product();
                    product.setName(resultSet.getString("product_name"));
                    product.setPrice(resultSet.getDouble("product_price"));
                    product.setId(resultSet.getInt("product_id"));

                    categoryDTO.getProducts().add(product);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categoryDTO;
    }

    @Override
    public boolean save(Category category) {
        int rowsAffected = 0;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CATEGORY)) {
            preparedStatement.setString(1, category.getName());
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean update(Category category, int id) {
        int rowsAffected = 0;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, id);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean remove(int id) {
        int rowsAffected = 0;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_CATEGORY)) {
            preparedStatement.setInt(1, id);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected > 0;
    }

    @Override
    public Category viewCategory(int id) {
        Category category = null;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY);
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    category = new Category();
                    category.setId(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return category;
    }

}
