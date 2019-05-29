package dao;

import exception.UserException;
import model.User;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author yzy
 * @Date 2019-05-27 23:24
 * @Version 1.0
 */
public interface UserDao {
    void addUser(User user) throws UserException;
}
