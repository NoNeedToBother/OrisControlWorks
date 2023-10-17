package ru.kpfu.itis.paramonov.dao;

import ru.kpfu.itis.paramonov.model.Master;
import ru.kpfu.itis.paramonov.model.Service;
import ru.kpfu.itis.paramonov.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao implements Dao<Service> {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Service get(int id) {
        try {
            String sql = "SELECT * from services WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                if (resultSet.next()) {
                    return new Service(
                            resultSet.getInt("id"),
                            resultSet.getInt("price"),
                            resultSet.getString("name"),
                            resultSet.getInt("duration")
                    );
                } else return null;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Service> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from services";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Service> services = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    services.add(
                            new Service(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("price"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("duration")
                            )
                    );
                }
            }
            return services;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
