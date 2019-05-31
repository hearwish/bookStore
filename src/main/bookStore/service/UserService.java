package service;

import exception.UserException;
import model.User;

import java.sql.SQLException;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author yzy
 * @Date 2019-05-28 00:05
 * @Version 1.0
 */
public interface UserService {
    void register(User user) throws UserException;

    /**
     * 激活用户
     *
     * @param activeCode
     */
    void activeUser(String activeCode) throws UserException;

    User loginUser(String username, String password) throws UserException;

    User findUserById(String id) throws UserException;

    void modifyUserInfoById(User user) throws UserException;
}
