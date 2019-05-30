package web.servlet;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //1.获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();

        try {
            User user = userService.loginUser(username, password);
            request.getSession().setAttribute("loginUser",user);
            //request.getRequestDispatcher("index.jsp").forward(request,response);
            String path;
            if (user.getRole().equals("管理员")){
                response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");

            }else {
                response.sendRedirect(request.getContextPath()+"/index.jsp");

            }

        } catch (UserException e) {
           request.setAttribute("login_msg",e.getMessage());
           request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
