package org.example.finalmodule3.model;


import org.example.finalmodule3.empty.RentalRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RoomModel {
    private final List<RentalRoom> list;
    private final Connection connection;

    public RoomModel() {
        list = new ArrayList<RentalRoom>();
        connection = Database.getConnection();

    }
    public ResultSet getAllUser() throws SQLException {
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

//    public void addUser(User user) throws SQLException {
//        String sql = "INSERT INTO student (id,name, email, class_id) VALUES (?,?,?,?)";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        //thay thế câu lệnh
//        statement.setInt(1, user.getId());
//        statement.setString(2, user.getName());
//        statement.setString(3, user.getEmail());
//        statement.setInt(4, user.getClass_id());
//        //thực thi câu lệnh
//        statement.execute();
//
//    }
    public ResultSet findUserByID(int roomID) throws SQLException {
        String sql = "SELECT * FROM student WHERE roomID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Thay thế tham số dấu ? trong câu truy vấn bằng giá trị id
        statement.setInt(1, roomID);

        // Thực thi truy vấn và trả về kết quả
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

//    public void updateUser(User user) throws SQLException {
//        String sql="UPDATE student SET name=?,email=?,class_id=? WHERE id=?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        //thay thế câu lệnh
//        statement.setInt(4, user.getId());
//        statement.setString(1, user.getName());
//        statement.setString(2, user.getEmail());
//        statement.setInt(3, user.getClass_id());
//
//        //thực thi câu lệnh
//        statement.execute();
//    }
//    public List<User> searchUsers(String keyword) throws SQLException {
//        String sql = "SELECT * FROM Student WHERE name LIKE ? OR email LIKE ?";
//        List<User> users = new ArrayList<>();
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            // Set the keyword for both the name and email fields
//            String searchKeyword = "%" + keyword + "%";
//            statement.setString(1, searchKeyword);
//            statement.setString(2, searchKeyword);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String name = resultSet.getString("name");
//                    String email = resultSet.getString("email");
//                    int class_id = resultSet.getInt("class_id");
//                    User user = new User(id, name, email, class_id);
//                    users.add(user);
//                }
//            }
//        }
//        return users;
//    }


//}

}
