package web.servlet;

import exception.UserException;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type","text/html;charset=utf-8");
        String activeCode = request.getParameter("activeCode");
        UserService us = new UserServiceImpl();

        try {
            us.activeUser(activeCode);
            response.getWriter().write("激活成功");
        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
