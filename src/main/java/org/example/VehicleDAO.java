package org.example;

import java.sql.*;
import java.util.ArrayList;

public class VehicleDAO {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";  // URL de Oracle
    private static final String DB_USER = "system";  // Usuario Oracle
    private static final String DB_PASS = "1234";  // Contraseña Oracle

    private Connection conn;

    public VehicleDAO() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar un vehículo
    public void saveVehicle(Vehicle vehicle) throws SQLException {
        String query = "INSERT INTO vehicles (license_plate, type, times_used, is_competition, electric, has_trunk, number_of_doors) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vehicle.getLicensePlate());
            stmt.setString(2, vehicle.getType());
            stmt.setInt(3, vehicle.getTimesUsed());
            stmt.setString(4, vehicle.isCompetition() ? "Y" : "N");  // 'Y' = true, 'N' = false
            stmt.setString(5, (vehicle instanceof Bicycle && ((Bicycle) vehicle).isElectric()) ? "Y" : "N");
            stmt.setString(6, (vehicle instanceof Motorbike && ((Motorbike) vehicle).hasTrunk()) ? "Y" : "N");
            stmt.setInt(7, (vehicle instanceof Car) ? ((Car) vehicle).getNumberOfDoors() : 0);  // Si no es coche, se pone 0

            stmt.executeUpdate();
        }
    }

    // Método para obtener un vehículo por su matrícula
    public Vehicle getVehicle(String licensePlate) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE license_plate = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, licensePlate);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");
                int timesUsed = rs.getInt("times_used");
                boolean isCompetition = "Y".equals(rs.getString("is_competition"));  // 'Y' -> true
                Vehicle vehicle = null;

                if ("Car".equalsIgnoreCase(type)) {
                    int numberOfDoors = rs.getInt("number_of_doors");
                    vehicle = new Car(licensePlate, timesUsed, isCompetition, numberOfDoors);
                } else if ("Motorbike".equalsIgnoreCase(type)) {
                    boolean hasTrunk = "Y".equals(rs.getString("has_trunk"));  // 'Y' -> true
                    vehicle = new Motorbike(licensePlate, timesUsed, isCompetition, hasTrunk);
                } else if ("Bicycle".equalsIgnoreCase(type)) {
                    boolean isElectric = "Y".equals(rs.getString("electric"));  // 'Y' -> true
                    vehicle = new Bicycle(licensePlate, timesUsed, isCompetition, isElectric);
                }

                return vehicle;
            }
            return null;
        }
    }

    // Método para actualizar un vehículo
    public void updateVehicle(Vehicle vehicle) throws SQLException {
        String query = "UPDATE vehicles SET license_plate = ?, type = ?, times_used = ?, is_competition = ?, electric = ?, has_trunk = ?, number_of_doors = ? " +
                "WHERE license_plate = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vehicle.getLicensePlate());
            stmt.setString(2, vehicle.getType());
            stmt.setInt(3, vehicle.getTimesUsed());
            stmt.setString(4, vehicle.isCompetition() ? "Y" : "N");  // 'Y' = true, 'N' = false
            stmt.setString(5, (vehicle instanceof Bicycle && ((Bicycle) vehicle).isElectric()) ? "Y" : "N");
            stmt.setString(6, (vehicle instanceof Motorbike && ((Motorbike) vehicle).hasTrunk()) ? "Y" : "N");
            stmt.setInt(7, (vehicle instanceof Car) ? ((Car) vehicle).getNumberOfDoors() : 0);
            stmt.setString(8, vehicle.getLicensePlate());

            stmt.executeUpdate();
        }
    }

    // Método para eliminar un vehículo
    public void deleteVehicle(String licensePlate) throws SQLException {
        String query = "DELETE FROM vehicles WHERE license_plate = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, licensePlate);
            stmt.executeUpdate();
        }
    }
}

