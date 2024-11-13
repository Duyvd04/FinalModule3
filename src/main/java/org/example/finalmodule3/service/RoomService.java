package org.example.finalmodule3.service;

import org.example.finalmodule3.empty.RentalRoom;
import org.example.finalmodule3.model.RoomModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class RoomService {
    private final RoomModel roomModel;

    public RoomService() {
        this.roomModel = new RoomModel();

    }

    public void showPageUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Show list of users
        ResultSet data = roomModel.getAllUser();
        List<RentalRoom> rentalrooms = new ArrayList<>();
        while (data.next()) {
            int roomID = data.getInt("roomID");
            String tenantName = data.getString("tenantName");
            String phoneNumber = data.getString("phoneNumber");
            Date rentalStartDate = data.getDate("rentalStartDate");
            String paymentMethodName = data.getString("paymentMethodName");
            String notes = data.getString("notes");
            RentalRoom rentalRoom = new RentalRoom(roomID, tenantName, phoneNumber, rentalStartDate, paymentMethodName, notes);
            rentalrooms.add(rentalRoom);

        }
        request.setAttribute("data", rentalrooms);//// Lưu danh sách người dùng vào request
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        roomModel.deleteUser(roomID);
        response.sendRedirect(request.getContextPath() + "/dd");
    }

    public void showPageAddUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/create.jsp");
        requestDispatcher.forward(request, response);
    }

    //
//    public void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
//        // Get user Data from request
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        int class_id = Integer.parseInt(request.getParameter("class_id"));
//        User user = new User(id, name, email, class_id);
//        // Lưu User vào cơ sở dữ liệu thông qua model
//        userModel.addUser(user);
//        // Chuyển hướng về trang danh sách người dùng
//        response.sendRedirect(request.getContextPath() +"/admin/users");
//    }
//
//    public void showPageUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//        //get user update from database
//        int id = Integer.parseInt(request.getParameter("id"));
//        User userUpdate = this.getUserById(id);
//        request.setAttribute("user", userUpdate);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/update.jsp");
//        requestDispatcher.forward(request, response);
//    }
//
//    public User getUserById(int id) throws SQLException {
//        ResultSet resultSet = userModel.findUserByID(id);
//        User user = null;
//        while (resultSet.next()) {
//            String name = resultSet.getString("name");
//            String email = resultSet.getString("email");
//            int class_id = resultSet.getInt("class_id");
//            user = new User(id, name, email, class_id);
//        }
//        return user;
//    }
//    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
//        // Get user Data from request
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        int class_id = Integer.parseInt(request.getParameter("class_id"));
//        User user = new User(id, name, email, class_id);
//        // Lưu User vào cơ sở dữ liệu thông qua model
//        userModel.updateUser(user);
//        // Chuyển hướng về trang danh sách người dùng
//        response.sendRedirect(request.getContextPath()+"/admin/users");
//    }
    public void Search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Get the keyword from the request
        String keyword = request.getParameter("keyword");

        // Get the list of rooms based on the search keyword
        List<RentalRoom> rooms = roomModel.searchRoom(keyword);

        // Set the search result to request
        request.setAttribute("data", rooms);

        // Forward to the list.jsp to display the results
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/list.jsp");
        requestDispatcher.forward(request, response);
    }
}





