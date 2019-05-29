package service;

import exception.UserException;
import model.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author yzy
 * @Date 2019-05-28 00:05
 * @Version 1.0
 */
public interface UserService {
    void register(User user) throws UserException;
}
