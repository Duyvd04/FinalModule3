package org.example.finalmodule3.model;


import org.example.finalmodule3.empty.RentalRoom;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import java.util.List;
public class RoomModel {
    private final List<RentalRoom> list;
    private final Connection connection;

    public RoomModel() {
        list = new ArrayList<RentalRoom>();
        connection = Database.getConnection();

    }
    public ResultSet getAllPayment() throws SQLException{
        // viet cau lenh truy van
        String sql = "SELECT * FROM PaymentMethod";
        //dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public ResultSet getAllRoom() throws SQLException {
        // viet cau lenh truy van
        String sql = "SELECT " +
                "    r.roomID, " +
                "    r.tenantName, " +
                "    r.phoneNumber, " +
                "    r.rentalStartDate, " +
                "    p.paymentMethodName, " +
                "    r.notes " +
                "FROM " +
                "    RentalRoom r " +
                "JOIN " +
                "    PaymentMethod p " +
                "ON " +
                "    r.paymentMethodID = p.paymentMethodID";
        //dua cau lenh truy van
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
    public void deleteUser(int roomID) throws SQLException {
        String sql = "DELETE FROM RentalRoom WHERE roomID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, roomID);
        statement.execute();
    }

    public void addUser(RentalRoom room) throws SQLException {
        String sql = "INSERT INTO RentalRoom (tenantName, phoneNumber, rentalStartDate, paymentMethodID, notes) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        //thay thế câu lệnh
        statement.setString(1, room.getTenantName());
        statement.setString(2, room.getPhoneNumber());
        statement.setDate(3, Date.valueOf(room.getRentalStartDate()));
        statement.setInt(4, room.getPaymentMethodID());
        statement.setString(5, room.getNotes());
        //thực thi câu lệnh
        statement.execute();

    }
    public ResultSet findUserByID(int roomID) throws SQLException {
        String sql = "SELECT * FROM RentalRoom WHERE roomID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Thay thế tham số dấu ? trong câu truy vấn bằng giá trị id
        statement.setInt(1, roomID);

        // Thực thi truy vấn và trả về kết quả
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public void updateUser(RentalRoom room) throws SQLException {
        String sql="UPDATE RentalRoom SET tenantName=?,phoneNumber=?, rentalStartDate=?, paymentMethodID=?,notes=? WHERE roomID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        //thay thế câu lệnh
        statement.setInt(6, room.getRoomID());
        statement.setString(1, room.getTenantName());
        statement.setString(2, room.getPhoneNumber());
        statement.setDate(3, Date.valueOf(room.getRentalStartDate()));
        statement.setInt(4, room.getPaymentMethodID());
        statement.setString(5, room.getNotes());
        //thực thi câu lệnh
        statement.execute();
    }
public List<RentalRoom> searchRoom(String keyword) throws SQLException {
    // SQL query to search for rooms by tenant name or phone number
    String sql = "SELECT r.roomID, r.tenantName, r.phoneNumber, r.rentalStartDate, p.paymentMethodName, r.notes " +
            "FROM RentalRoom r " +
            "JOIN PaymentMethod p ON r.paymentMethodID = p.paymentMethodID " +
            "WHERE r.tenantName LIKE ? OR r.phoneNumber LIKE ?";

    List<RentalRoom> rooms = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        String searchKeyword = "%" + keyword + "%";
        statement.setString(1, searchKeyword);
        statement.setString(2, searchKeyword);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int roomID = resultSet.getInt("roomID");
                String tenantName = resultSet.getString("tenantName");
                String phoneNumber = resultSet.getString("phoneNumber");
                LocalDate rentalStartDate = resultSet.getDate("rentalStartDate").toLocalDate();
                String paymentMethod = resultSet.getString("paymentMethodName");
                String notes = resultSet.getString("notes");
                RentalRoom room = new RentalRoom(roomID, tenantName, phoneNumber, rentalStartDate, paymentMethod, notes);
                rooms.add(room);
            }
        }
    }
    return rooms;
}


}



//}


