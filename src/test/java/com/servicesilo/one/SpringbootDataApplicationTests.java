package com.servicesilo.one;

import com.servicesilo.one.datasource.CommonDAO;
import com.servicesilo.one.model.ServiceUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    RedisTemplate<String, Object> template;

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

    }

    @Test
    public void testRedis() {
        template.opsForValue().set("123", "test");
        System.out.println(template.opsForValue().get("123"));
    }
}
