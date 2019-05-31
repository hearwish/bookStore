package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import exception.UserException;
import model.User;
import service.UserService;
import utils.SendEmail;

import java.sql.SQLException;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author yzy
 * @Date 2019-05-28 00:05
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {


    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    public void register(User user) throws UserException {
        try {
            userDao.addUser(user);
            //发送激活邮件
            String link = "http://localhost:8080/bookstore/active?activeCode=" + user.getActiveCode();
            String html = "<a href=\"" + link + "\">傻逼辛巴！！！</a>";
            System.out.println(html);

            SendEmail.sendMail(user.getEmail(), html);

        } catch (UserException e) {
            e.printStackTrace();
            throw new UserException("用户注册失败！用户名重复！");
        }
    }

    /**
     * 激活用户
     *
     * @param activeCode
     */
    @Override
    public void activeUser(String activeCode) throws UserException {
        User user;
        try {
            user = userDao.findActiveUserDao(activeCode);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("激活失败");
        }

        if (user == null) {
            throw new UserException("非法激活，用户不存在！");
        }
        if (user != null && user.getState() == 1) {
            throw new UserException("用户已经激活！");
        }

        try {

            if (user != null && user.getState() != 1) {
                userDao.updateStateByActiveCode(activeCode);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new UserException("激活失败");
        }


    }

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @Override
    public User loginUser(String username, String password) throws UserException {
        User user = null;
        try {
            user = userDao.findUserByUserName(username, password);
            if (user == null){
                throw new UserException("用户名或密码错误");
            }
            if (user.getState() == 0){
                throw new UserException("请激活用户！");
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("登陆失败，请稍后再试");
        }

    }

    @Override
    public User findUserById(String id) throws UserException {
        User user = null;
        try {
            user = userDao.findUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("后台错误，请稍后再试试！");
        }
        return user;
    }

    @Override
    public void modifyUserInfoById(User user) throws UserException {
        try {
            userDao.modifyUserInfo(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("更新用户错误！");
        }

    }


}
