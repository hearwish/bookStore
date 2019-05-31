package web.servlet;

import com.sun.mail.imap.protocol.ID;
import exception.UserException;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/findUserById")
public class FindUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        UserService us = new UserServiceImpl();
        try {
            User userById = us.findUserById(userId);
            //el表达式取数据顺序page,request,session,application
            request.setAttribute("u",userById);
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request,response);
        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
