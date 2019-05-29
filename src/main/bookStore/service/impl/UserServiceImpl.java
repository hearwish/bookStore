package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import exception.UserException;
import model.User;
import service.UserService;

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
     * 创建dao
     * @param user
     */
    @Override
    public void register(User user)throws UserException{
        try {
            userDao.addUser(user);

        }catch (UserException e){
            e.printStackTrace();
            throw new UserException("用户注册失败！用户名重复！");
        }
    }
}
