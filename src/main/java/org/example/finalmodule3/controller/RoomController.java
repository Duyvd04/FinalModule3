package org.example.finalmodule3.controller;

import org.example.finalmodule3.service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RoomController", urlPatterns = "/dd/*")
public class RoomController extends BaseController {
    private RoomService roomService;


    @Override
    public void init() throws ServletException {
        roomService = new RoomService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String url = req.getPathInfo();
        if ((url == null)) {
            url = "/";
        }
        try {
            switch (url) {
                case "/":
                    roomService.showPageUserList(req, resp);
                    break;
                case "/create":
                    roomService.showPageAddUser(req, resp);
                    break;
                case "/delete":
                    roomService.deleteUser(req, resp);
                    break;
                case "/find":
                    roomService.Search(req, resp);
                    break;
//                case "/update":
//                    roomService.showPageUpdateUser(req, resp);
//                    break;
                default:
                    pageNotFound(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);

        }
    }
//
//    @Override
//    // Servlet này chịu trách nhiệm xử lý các yêu cầu HTTP POST từ phía client.
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // Handle POST requests here
//        req.setCharacterEncoding("UTF-8");
//        String url = req.getPathInfo();
//        if ((url == null)) {
//            url = "/";
//        }
//        try {
//            switch (url) {
//                case "/create":
//                    roomService.createUser(req, resp);
//                    break;
//                case "/update":
//                    roomService.updateUser(req, resp);
//                    break;
//                default:
////                    pageNotFound(req, resp);
//                    break;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new ServletException(e);
//        }
//
//    }
}