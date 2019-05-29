package web.servlet;

import exception.UserException;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.UUID;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("utf-8");已经在专门的过滤器处理乱码问题
        //1.把参数转成Bean.model
        String checkCode = request.getParameter("checkCode");
        String checkCode_session =(String) request.getSession().getAttribute("checkcode_session");
        if (checkCode.equalsIgnoreCase(checkCode_session)) {
            System.out.println("验证码输入正确！");
            User user = new User();
            try {
                BeanUtils.populate(user, request.getParameterMap());
                user.setActiveCode(UUID.randomUUID().toString());
                user.setRole("普通用户");
                user.setRegistTime(new Date());
                System.out.println(user);
                //2.注册
                UserService us = new UserServiceImpl();
                us.register(user);
                //3.返回结果
                request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);

            } catch (UserException e) {
                e.printStackTrace();
                request.setAttribute("register_err", e.getMessage());
                request.getRequestDispatcher("/register.jsp").forward(request, response);

            } catch (Exception e) {
                System.out.println("参数转换模型失败！");
                e.printStackTrace();
            }
        }else {
            //验证码输入不正确
            request.setAttribute("checkCode_err", "验证码错误！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
