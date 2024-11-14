package org.example.finalmodule3.service;

import org.example.finalmodule3.empty.PaymentMethod;
import org.example.finalmodule3.empty.RentalRoom;
import org.example.finalmodule3.model.RoomModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        ResultSet data = roomModel.getAllRoom();
        ResultSet result = roomModel.getAllPayment();
        List<RentalRoom> rentalrooms = new ArrayList<>();
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        while (result.next()) {
            int paymentMethodID = result.getInt("paymentMethodID");
            String paymentMethodName = result.getString("paymentMethodName");
            PaymentMethod paymentMethod = new PaymentMethod(paymentMethodID, paymentMethodName);
            paymentMethods.add(paymentMethod);
        }
        while (data.next()) {
            int roomID = data.getInt("roomID");
            String tenantName = data.getString("tenantName");
            String phoneNumber = data.getString("phoneNumber");
            LocalDate rentalStartDate = data.getDate("rentalStartDate").toLocalDate();
            String paymentMethodName = data.getString("paymentMethodName");
            String notes = data.getString("notes");
            RentalRoom rentalRoom = new RentalRoom(roomID, tenantName, phoneNumber, rentalStartDate, paymentMethodName, notes);
            rentalrooms.add(rentalRoom);

        }
        request.setAttribute("data", rentalrooms);//// Lưu danh sách người dùng vào request
        request.setAttribute("result", paymentMethods);  // Lưu danh sách phương thức thanh toán vào request
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        roomModel.deleteUser(roomID);
        response.sendRedirect(request.getContextPath() + "/dd");
    }

    public void showPageAddRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view.jsp");
        requestDispatcher.forward(request, response);
    }


    public void createRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        // Get user Data from request
        String tenantName = request.getParameter("tenantName");
        String phoneNumber = request.getParameter("phoneNumber");
        LocalDate rentalStartDate = LocalDate.parse(request.getParameter("rentalStartDate"));
        int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
        String notes = request.getParameter("notes");
        RentalRoom room = new RentalRoom(tenantName, phoneNumber, rentalStartDate, paymentMethodID,notes);
        // Lưu User vào cơ sở dữ liệu thông qua model
        roomModel.addUser(room);
        // Chuyển hướng về trang danh sách người dùng
        response.sendRedirect(request.getContextPath() +"/dd");
    }

    public void showPageUpdateRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //get user update from database
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        RentalRoom roomUpdate = this.getRoomById(roomID);
        request.setAttribute("room", roomUpdate);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/update.jsp");
        requestDispatcher.forward(request, response);
    }

    public RentalRoom getRoomById(int roomID) throws SQLException {
        ResultSet resultSet = roomModel.findUserByID(roomID);
        RentalRoom room = null;
        while (resultSet.next()) {
            String tenantName = resultSet.getString("tenantName");
            String phoneNumber = resultSet.getString("phoneNumber");
            LocalDate rentalStartDate = resultSet.getDate("rentalStartDate").toLocalDate();
            int paymentMethodID = resultSet.getInt("paymentMethodID");
            String notes = resultSet.getString("notes");
            room = new RentalRoom(roomID,tenantName, phoneNumber, rentalStartDate, paymentMethodID,notes);
        }
        return room;
    }
    public void updateRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        // Get user Data from request
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        String tenantName = request.getParameter("tenantName");
        String phoneNumber = request.getParameter("phoneNumber");
        LocalDate rentalStartDate = LocalDate.parse(request.getParameter("rentalStartDate"));
        int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
        String notes = request.getParameter("notes");
        RentalRoom room = new RentalRoom(roomID,tenantName, phoneNumber, rentalStartDate, paymentMethodID, notes);
        // Lưu User vào cơ sở dữ liệu thông qua model
        roomModel.updateUser(room);
        // Chuyển hướng về trang danh sách người dùng
        response.sendRedirect(request.getContextPath()+"/dd");
    }
    public void Search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
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





