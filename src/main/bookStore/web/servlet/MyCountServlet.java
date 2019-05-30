package web.servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName MyCountServlet
 * @Description TODO
 * @Author yzy
 * @Date 2019-05-30 23:39
 * @Version 1.0
 */
@WebServlet("/myAccount")
public class MyCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser == null){
            System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath()+"/login.jsp");

        }

        if (loginUser != null){
            response.sendRedirect(request.getContextPath()+"/myAccount.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
