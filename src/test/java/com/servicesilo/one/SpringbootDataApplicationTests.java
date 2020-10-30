package com.servicesilo.one;

import com.servicesilo.one.datasource.CommonDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDataApplicationTests {
    @Autowired
    DataSource dataSource;

    @Autowired
    CommonDAO dao;

    @Test
    public void contextLoads() throws SQLException {
//       查看默认数据源 class com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());
        //获取数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
//        关闭连接
        connection.close();
    }

    @Test
    public void testTemplate() {
        String sql = "insert into service_user(uuid, user_name, password) values(?, ?, ?) ";
        dao.addOrUpdate(sql, "0", "admin", "admin");
    }
}
