package com.wood.crowd;

import com.wood.crowd.entity.Admin;
import com.wood.crowd.mapper.AdminMapper;
import com.wood.crowd.service.AdminService;
import com.wood.crowd.util.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;

    @Test
    public void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testInsertAdmin() {
        Admin admin = new Admin(null, "test", "Aa123456", "test", "test@qq.com", null);
        adminService.saveAdmin(admin);
    }

    @Test
    public void testSelect() {
        Admin admin = adminMapper.selectByPrimaryKey(1);
        System.out.println(admin);
    }
}
