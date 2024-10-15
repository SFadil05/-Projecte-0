package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VehicleGUI extends JFrame {

    private final VehicleDAO vehicleDAO;

    public VehicleGUI() {
        vehicleDAO = new VehicleDAO();
        setTitle("Parking Vehicle Management");
        setSize(700, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JButton btnAdd = new JButton("Add Vehicle");
        JButton btnSearch = new JButton("Search Vehicle");
        JButton btnDelete = new JButton("Delete Vehicle");

        // Cambiar el color de fondo de los botones
        btnAdd.setBackground(new Color(0, 150, 136)); // Color verde/azul oscuro
        btnAdd.setForeground(Color.WHITE);  // Color de texto blanco

        btnSearch.setBackground(new Color(255, 87, 34)); // Color naranja
        btnSearch.setForeground(Color.WHITE);  // Color de texto blanco

        btnDelete.setBackground(new Color(244, 67, 54)); // Color rojo
        btnDelete.setForeground(Color.WHITE);  // Color de texto blanco


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicle();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchVehicle();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteVehicle();
            }
        });

        panel.add(btnAdd);
        panel.add(btnSearch);
        panel.add(btnDelete);

        add(panel);
    }

    private void addVehicle() {
        String licensePlate = JOptionPane.showInputDialog(this, "Enter License Plate:");
        int timesUsed = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter number of times used:"));
        boolean isCompetition = JOptionPane.showConfirmDialog(this, "Is it a competition vehicle?", "Competition", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        String vehicleType = JOptionPane.showInputDialog(this, "Enter vehicle type (Car, Motorbike, Bicycle):");
        Vehicle vehicle = null;

        if ("Car".equalsIgnoreCase(vehicleType)) {
            int numberOfDoors = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter number of doors:"));
            vehicle = new Car(licensePlate, timesUsed, isCompetition, numberOfDoors);
        } else if ("Motorbike".equalsIgnoreCase(vehicleType)) {
            boolean hasTrunk = JOptionPane.showConfirmDialog(this, "Does it have a trunk?", "Trunk", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            vehicle = new Motorbike(licensePlate, timesUsed, isCompetition, hasTrunk);
        } else if ("Bicycle".equalsIgnoreCase(vehicleType)) {
            boolean isElectric = JOptionPane.showConfirmDialog(this, "Is it electric?", "Electric", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            vehicle = new Bicycle(licensePlate, timesUsed, isCompetition, isElectric);
        }

        if (vehicle != null) {
            try {
                vehicleDAO.saveVehicle(vehicle);
                JOptionPane.showMessageDialog(this, "Vehicle added successfully!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error adding vehicle: " + e.getMessage());
            }
        }
    }

    private void searchVehicle() {
        String licensePlate = JOptionPane.showInputDialog(this, "Enter License Plate:");
        try {
            Vehicle vehicle = vehicleDAO.getVehicle(licensePlate);
            if (vehicle != null) {
                JOptionPane.showMessageDialog(this, vehicle.getVehicleDetails());
            } else {
                JOptionPane.showMessageDialog(this, "Vehicle not found.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching for vehicle: " + e.getMessage());
        }
    }

    private void deleteVehicle() {
        String licensePlate = JOptionPane.showInputDialog(this, "Enter License Plate to delete:");
        try {
            vehicleDAO.deleteVehicle(licensePlate);
            JOptionPane.showMessageDialog(this, "Vehicle deleted successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting vehicle: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VehicleGUI gui = new VehicleGUI();
            gui.setVisible(true);
        });
    }
}
