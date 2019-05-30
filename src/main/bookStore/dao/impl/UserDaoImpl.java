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

    @Override
    public User findActiveUserDao(String activeCode) throws SQLException {
        sql = "select * from user where activeCode=?  ";

            return qr.query(sql, new BeanHandler<User>(User.class),activeCode);



    }

    @Override
    public void updateStateByActiveCode(String activeCode) throws SQLException {
        sql = "update user set state=1 where activeCode=?";

            qr.update(sql,activeCode);

    }

    @Override
    public User findUserByUserName(String name, String password) throws SQLException {
        sql = "select * from user where username = ? and password = ?";
        User user = qr.query(sql, new BeanHandler<User>(User.class), name,password);
        return user;

    }
}
