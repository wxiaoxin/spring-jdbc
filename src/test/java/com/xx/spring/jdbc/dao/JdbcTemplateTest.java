package com.xx.spring.jdbc.dao;

import com.xx.spring.jdbc.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * Created by wxiao on 2016.9.20.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JdbcTemplateTest {


    private JdbcTemplate jt;

    @Autowired
    private void setJt(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }


    @Test
    public void testExecute() {
        // 创建表
//        String sql = "create table a(id int)";

        // 添加列
//        String sql = "ALTER TABLE a ADD name VARCHAR(20) NOT NULL";

        // 删除列
//        String sql = "ALTER TABLE a DROP name";

        // 修改列
//        String sql = "ALTER TABLE a "


        String sql = "CREATE TABLE t_user (" +
                "id VARCHAR(64) PRIMARY KEY COMMENT '主键'," +
                "name VARCHAR(20) NOT NULL COMMENT '用户名'," +
                "pwd VARCHAR(20) NOT NULL COMMENT '密码'," +
                "status INT NOT NULL COMMENT '状态码：0-正常 -1-删除 1-禁用'" +
                ")";

        jt.execute(sql);
    }


    /**
     * update用于添加数据操作
     */
    @Test
    public void testInsert() {
        // 无参
//        String sql = "insert into a values(222,'222')";
//        int result = jt.update(sql);

        // 有参
        String sql = "insert into a values(?, ?)";
        // 变长Object类型参数
        int result = jt.update(sql, 123, "123");

        // Object类型数组
//        int result = jt.update(sql, new Object[]{234, "234"});

        System.out.println(result);
    }


    /**
     * update用于删除数据
     */
    @Test
    public void testDel() {

        // 无参
//        String sql = "delete from a where id=456";
//        int result = jt.update(sql);

        // 有参
        String sql = "delete from a where id=? ";
//        int result = jt.update(sql, 111);

        int result = jt.update(sql, new Object[]{111});

        System.out.println(result);
    }


    /**
     * update用于修改数据
     */
    @Test
    public void testUpdate() {

        // 无参
//        String sql = "update a set name='aaa' where id=111 ";
//        int result = jt.update(sql);

        // 有参
        String sql = "update a set name=? where id=? ";
//        int result = jt.update(sql, 333);
        int result = jt.update(sql, new Object[]{"haaa",222});

        System.out.println(result);

    }

    /**
     * 查询
     * ResultSetExtractor 用于结果集数据提取，用户需实现方法extractData(ResultSet rs)来处理结果集，用户必须处理整个结果集
     */
    @Test
    public void query1() {

        // 无参
        String sql = "select * from t_user t where t.id='444652e3-c95a-493b-b385-7f81d7f8a546'";
        User user = jt.query(sql, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                User user = new User();
                while (rs.next()) {
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPwd(rs.getString("pwd"));
                    user.setStatus(rs.getInt("status"));
                }
                return user;
            }
        });
        System.out.println(user);


        // 有参
//        String sql2 = "select * from t_user t where t.id=? and t.status=? ";

        String sql2 = "select * from t_user t where t.id=? ";
        User user2 = jt.query(sql2, new Object[]{"444652e3-c95a-493b-b385-7f81d7f8a546"}, new int[]{Types.VARCHAR}, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                User user = new User();
                while (rs.next()) {
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPwd(rs.getString("pwd"));
                    user.setStatus(rs.getInt("status"));
                }
                return user;
            }
        });

//        User user = jt.query(sql, new ResultSetExtractor<User>() {
//            @Override
//            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
//                User user = new User();
//                while (rs.next()) {
//                    user.setId(rs.getString("id"));
//                    user.setName(rs.getString("name"));
//                    user.setPwd(rs.getString("pwd"));
//                    user.setStatus(rs.getInt("status"));
//                }
//                return user;
//            }
//        }, "", -1);

        System.out.println(user2);

    }



    /**
     * RowCallbackHandler 用于处理ResultSet的每一行结果，用户需实现方法processRow(ResultSet rs)来完成处理
     * 在该回调方法中无需执行rs.next()，该操作由JdbcTemplate来执行，用户只需按行获取数据然后处理即可
     */
    @Test
    public void query2() {

        String sql = "select * from t_user t where t.id=? ";

//        jt.query(sql, new Object[]{"444652e3-c95a-493b-b385-7f81d7f8a546"}, new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                User user = new User();
//                user.setId(rs.getString("id"));
//                user.setName(rs.getString("name"));
//                user.setPwd(rs.getString("pwd"));
//                user.setStatus(rs.getInt("status"));
//                System.out.println(user);
//            }
//        });

        jt.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setStatus(rs.getInt("status"));
                System.out.println(user);
            }
        }, new Object[]{"444652e3-c95a-493b-b385-7f81d7f8a546"});

    }

    /**
     * RowMapper 用于将结果集每行数据转换为需要的类型，用户需实现方法mapRow(ResultSet rs, int rowNum)来完成将每行数据转换为相应的类型
     */
    @Test
    public void query3() {
        String sql = "select * from t_user t";
        List<User> userList = jt.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setStatus(rs.getInt("status"));
                return user;
            }
        });
        System.out.println(userList);
    }


    /**
     * queryForList 将查询结果包装成Map<String, Object>类型的数据
     */
    @Test
    public void query4() {

        // 查询结果
        List<Map<String, Object>> result;

        // 无参
        String sql = "select * from t_user t";
//        result = jt.queryForList(sql);


        // 有参
        String sql2 = "select * from t_user t where t.status=? ";
        result = jt.queryForList(sql2, 0);
//        for(Map<String, Object> map : result) {
//            User user = new User();
//            user.setId(String.valueOf(map.get("id")));
//            user.setName(String.valueOf(map.get("name")));
//            user.setPwd(String.valueOf(map.get("pwd")));
//            user.setStatus(Integer.valueOf(String.valueOf(map.get("status"))));
//            System.out.println(user);
//        }


        /**
         * queryForList(String sql, Class<T> elementTypes, Object... args)
         * 只能用于查询单独列值
         */
        String sql3 = "select name from t_user t where t.status=? ";
        List<String> userList = jt.queryForList(sql3, String.class, 0);
        for(String user : userList) {
            System.out.println(userList);
        }

    }


    /**
     * 查询单行数据，并将结果封装在Map<String, Object>中
     */
    @Test
    public void query5() {

        String sql = "select * from t_user t where t.id=? ";
        Map<String, Object> map = jt.queryForMap(sql, "444652e3-c95a-493b-b385-7f81d7f8a546");
        User user = new User();
        user.setId(String.valueOf(map.get("id")));
        user.setName(String.valueOf(map.get("name")));
        user.setPwd(String.valueOf(map.get("pwd")));
        user.setStatus(Integer.valueOf(String.valueOf(map.get("status"))));
        System.out.println(user);

    }


    /**
     * 返回单行单列的值
     */
    @Test
    public void query6() {

        String name;

        String sql = "select t.name from t_user t where t.id='444652e3-c95a-493b-b385-7f81d7f8a546' ";
        name = jt.queryForObject(sql, String.class);

        String sql2 = "select t.name from t_user t where t.id=? ";
//        name = jt.queryForObject(sql2, String.class, "444652e3-c95a-493b-b385-7f81d7f8a546");
        name = jt.queryForObject(sql2, new Object[]{"444652e3-c95a-493b-b385-7f81d7f8a546"}, String.class);

//        System.out.println(name);


        String sql3 = "select t.* from t_user t where t.id='444652e3-c95a-493b-b385-7f81d7f8a546'";
        User user = jt.queryForObject(sql3, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setStatus(rs.getInt("status"));
                return user;
            }
        });
        System.out.println(user);


        String sql4 = "select t.* from t_user t where t.id=? ";
        User user2 = jt.queryForObject(sql4, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setStatus(rs.getInt("status"));
                return user;
            }
        }, "444652e3-c95a-493b-b385-7f81d7f8a546");
        System.out.println(user2);

    }

}
