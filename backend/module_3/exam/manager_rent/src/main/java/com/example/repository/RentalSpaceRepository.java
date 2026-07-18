package com.example.repository;

import com.example.entity.RentalSpace;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalSpaceRepository extends BaseRepository implements IRentalSpaceRepository {

    private static final String SELECT_ALL = "SELECT * FROM rental_space WHERE 1=1";
    private static final String INSERT_SQL = "INSERT INTO rental_space (code, area, status, floor, type, price) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM rental_space WHERE code = ?";

    @Override
    public List<RentalSpace> findAll(String type, Integer floor) {
        List<RentalSpace> rentalSpaces = new ArrayList<>();
        StringBuilder query = new StringBuilder(SELECT_ALL);

        if (type != null && !type.isEmpty()) {
            query.append(" AND type LIKE ?");
        }
        if (floor != null) {
            query.append(" AND floor = ?");
        }
        query.append(" ORDER BY area ASC");

        try (Connection connection = getConnection()) {
            if (connection == null)
                return rentalSpaces;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {

                int paramIndex = 1;
                if (type != null && !type.isEmpty()) {
                    preparedStatement.setString(paramIndex++, "%" + type + "%");
                }
                if (floor != null) {
                    preparedStatement.setInt(paramIndex, floor);
                }

                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    RentalSpace rentalSpace = new RentalSpace();
                    rentalSpace.setId(rs.getInt("id"));
                    rentalSpace.setCode(rs.getString("code"));
                    rentalSpace.setArea(rs.getDouble("area"));
                    rentalSpace.setStatus(rs.getString("status"));
                    rentalSpace.setFloor(rs.getInt("floor"));
                    rentalSpace.setType(rs.getString("type"));
                    rentalSpace.setPrice(rs.getDouble("price"));
                    rentalSpaces.add(rentalSpace);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalSpaces;
    }

    @Override
    public boolean save(RentalSpace rentalSpace) {
        try (Connection connection = getConnection()) {
            if (connection == null)
                return false;
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
                preparedStatement.setString(1, rentalSpace.getCode());
                preparedStatement.setDouble(2, rentalSpace.getArea());
                preparedStatement.setString(3, rentalSpace.getStatus());
                preparedStatement.setInt(4, rentalSpace.getFloor());
                preparedStatement.setString(5, rentalSpace.getType());
                preparedStatement.setDouble(6, rentalSpace.getPrice());
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String code) {
        try (Connection connection = getConnection()) {
            if (connection == null)
                return false;
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
                preparedStatement.setString(1, code);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
