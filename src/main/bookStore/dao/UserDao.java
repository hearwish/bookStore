package dao;

import exception.UserException;
import model.User;

import java.sql.SQLException;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author yzy
 * @Date 2019-05-27 23:24
 * @Version 1.0
 */
public interface UserDao {
    void addUser(User user) throws UserException;
    User findActiveUserDao(String activeCode) throws SQLException;
    void updateStateByActiveCode(String activeCode) throws SQLException;
    User findUserByUserName(String name,String password) throws SQLException;
}
