package ru.kpfu.itis.paramonov.dao;

import ru.kpfu.itis.paramonov.model.Appointment;
import ru.kpfu.itis.paramonov.model.Master;
import ru.kpfu.itis.paramonov.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao implements Dao<Appointment>{
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Appointment get(int id) {
        try {
            String sql = "SELECT * from appointments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                return new Appointment(
                        resultSet.getInt("id"),
                        resultSet.getInt("masterId"),
                        resultSet.getInt("serviceId"),
                        resultSet.getTimestamp("time"),
                        resultSet.getString("customer")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from appointments";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Appointment> appointments = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    appointments.add(
                            new Appointment(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("masterid"),
                                    resultSet.getInt("serviceid"),
                                    resultSet.getTimestamp("time"),
                                    resultSet.getString("customer")
                            )
                    );
                }
            }
            return appointments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Appointment> getMasterAppointments(int masterId) {
        try {
            String sql = "SELECT * from appointments where masterid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, masterId);
            ResultSet resultSet = statement.executeQuery();
            List<Appointment> appointments = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    appointments.add(
                            new Appointment(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("masterid"),
                                    resultSet.getInt("serviceid"),
                                    resultSet.getTimestamp("time"),
                                    resultSet.getString("customer")
                            )
                    );
                }
            }
            return appointments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Appointment appointment) {
        String sql = "insert into appointments (masterid, serviceid, time, customer) values (?, ?, ?, ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, appointment.getMasterId());
            statement.setInt(2, appointment.getServiceId());
            statement.setTimestamp(3, appointment.getTime());
            statement.setString(4, appointment.getClientNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
