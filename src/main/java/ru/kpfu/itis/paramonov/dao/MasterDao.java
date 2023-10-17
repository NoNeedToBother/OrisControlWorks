package ru.kpfu.itis.paramonov.dao;

import ru.kpfu.itis.paramonov.model.Master;
import ru.kpfu.itis.paramonov.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterDao implements Dao<Master>{
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Master get(int id) {
        try {
            String sql = "SELECT * from masters WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                if (resultSet.next()) {
                    return new Master(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("lastname"),
                            resultSet.getString("begin_working"),
                            resultSet.getString("finish_working")
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
    public List<Master> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from masters";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Master> masters = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    masters.add(
                            new Master(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("lastname"),
                                    resultSet.getString("begin_working"),
                                    resultSet.getString("finish_working")
                            )
                    );
                }
            }
            return masters;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
