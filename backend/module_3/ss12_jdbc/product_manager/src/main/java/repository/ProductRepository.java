package repository;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ProductRepository extends BaseRepository implements IProductRepository {
    private static final String SELECT_ALL_PRODUCTS = "select * from products order by id desc";
    private static final String ADD_PRODUCT = "insert into products(name, price, category_id) values(?,?, ?)";
    private static final String UPDATE_PRODUCT = "update products set name = ?, price = ?, category_id = ? where id = ? ";
    private static final String REMOVE_PRODUCT = "delete from products where id = ? ";
    private static final String SELECT_PRODUCT = "select * from products where id = ?";
    private static final String SELECT_PRODUCT_BY_NAME_OR_CATEGORY = "select * from products where lower(name) like lower(?) and (?=0 or category_id = ?) order by id desc";

    @Override
    public List<Product> getProductList() {
        List<Product> list = new ArrayList<>();
        Product product = null;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("category_id"));
                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Product product) {
        int rowsAffected = 0;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getCategoryId());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean update(Product product, int id) {
        int rowsAffected = 0;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getCategoryId());

            preparedStatement.setInt(4, id);
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
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_PRODUCT)) {
            preparedStatement.setInt(1, id);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected > 0;
    }

    @Override
    public Product viewProduct(int id) {
        Product product = null;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT);
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setCategoryId(resultSet.getInt("category_id"));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findProductByName(String name, int categoryId) {
        List<Product> list = new ArrayList<>();
        Product product = null;
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME_OR_CATEGORY);
        ) {
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setInt(2, categoryId);
            preparedStatement.setInt(3, categoryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setCategoryId(resultSet.getInt("category_id"));
                    list.add(product);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
