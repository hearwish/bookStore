package dao.impl;

import dao.UserDao;
import exception.UserException;
import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.C3P0Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author yzy
 * @Date 2019-05-27 23:23
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {

    private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
    private String sql = null;

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void addUser(User user) throws UserException {
        //1.获取queryRunner

        //2.sql
        sql = "insert into user ";
        sql += "(username,PASSWORD,gender,email,telephone,introduce,activeCode,state,role,registTime)";
        sql += " values(?,?,?,?,?,?,?,?,?,?)";
        //3.参数
        List<Object> list = new ArrayList<>();
        list.add(user.getUsername());
        list.add(user.getPassword());
        list.add(user.getGender());
        list.add(user.getEmail());
        list.add(user.getTelephone());
        list.add(user.getIntroduce());
        list.add(user.getActiveCode());
        list.add(user.getState());
        list.add(user.getRole());
        list.add(user.getRegistTime());
        try {
            qr.update(sql, list.toArray());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserException("用户注册失败！用户名重复！");
        }


    }

    /**
     * 根据激活码查找用户
     * @param activeCode
     * @return
     * @throws SQLException
     */
    @Override
    public User findActiveUserDao(String activeCode) throws SQLException {
        sql = "select * from user where activeCode=?  ";

            return qr.query(sql, new BeanHandler<User>(User.class),activeCode);



    }

    /**
     * 激活用户
     * @param activeCode
     * @throws SQLException
     */
    @Override
    public void updateStateByActiveCode(String activeCode) throws SQLException {
        sql = "update user set state=1 where activeCode=?";
            qr.update(sql,activeCode);

    }

    /**
     * 用户登陆
     * @param name
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public User findUserByUserName(String name, String password) throws SQLException {
        sql = "select * from user where username = ? and password = ?";
        User user = qr.query(sql, new BeanHandler<User>(User.class), name,password);
        return user;

    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public User findUserById(String id) throws SQLException {
        sql = "select * from user where id = ?";
        User user = qr.query(sql,new BeanHandler<User>(User.class),id);
        return user;

    }

    /**
     * 更改用户
     * @param user
     * @throws SQLException
     */
    @Override
    public void modifyUserInfo(User user) throws SQLException {
        sql = "update user set password=?,gender=?,telephone=? where id=?";
        qr.update(sql,user.getPassword(),user.getGender(),user.getTelephone(),user.getId());
    }
}
