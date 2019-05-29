package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName C3P0Utils
 * @Description TODO
 * @Author yzy
 * @Date 2019-05-27 23:17
 * @Version 1.0
 */
public class C3P0Utils {
    private static DataSource ds  = new ComboPooledDataSource();
    public static DataSource getDataSource(){
        return ds;
    }
    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("服务器错误");
        }
    }
    public static void closeAll(Connection conn, Statement statement, ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                conn = null;
            }
        }
    }

}
