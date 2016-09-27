package com.xx.spring.jdbc.dao.impl;

import com.xx.spring.jdbc.base.BaseDao;
import com.xx.spring.jdbc.dao.IUserDao;
import com.xx.spring.jdbc.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxiao on 2016.9.19.
 * 用户User实体操作类
 */

@Repository
public class UserDao extends BaseDao implements IUserDao {

    @Override
    public int save(User user) {
        String sql = "insert into t_user values(?,?,?,?,?)";
        return jt.update(sql, user.getId(), user.getName(), user.getPwd(), user.getStatus(), user.getCreateTime());
    }

    @Override
    public int[] batchSave(List<User> userList) {
        String sql = "insert into t_user values(?,?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (User user : userList) {
            Object[] arg = new Object[]{
                    user.getId(), user.getName(), user.getPwd(), user.getStatus(), user.getCreateTime()
            };
            batchArgs.add(arg);
        }
        return jt.batchUpdate(sql, batchArgs);
    }

    @Override
    public int del(User user) {
        String sql = "delete from t_user where id=? ";
        return jt.update(sql, user.getId());
    }


    @Override
    public int update(User user) {
        String sql = "update t_user set name=? ,pwd=? ,status=? where id=? ";
        return jt.update(sql, user.getName(), user.getPwd(), user.getStatus(), user.getId());
    }

    @Override
    public int[] batchUpdate(final List<User> userList) {

        String sql = "update t_user set name=? ,pwd=? ,status=? ,create_time=? where id=? ";

        // 使用JdbcTemplate实现批处理
        /*return jt.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user = userList.get(i);
                ps.setString(1, user.getName());
                ps.setString(2, user.getPwd());
                ps.setInt(3, user.getStatus());
                // util.Date转sql.Date
                ps.setDate(4, new Date(user.getCreateTime().getTime()));
                ps.setString(5, user.getId());
            }

            @Override
            public int getBatchSize() {
                return userList.size();
            }
        });*/


        List<Object[]> batchArgs = new ArrayList<>();
        for (User user : userList) {
            Object[] arg = new Object[]{
                user.getName(), user.getPwd(), user.getStatus(), user.getCreateTime(), user.getId()
            };
            batchArgs.add(arg);
        }
        return jt.batchUpdate(sql, batchArgs);

    }


    @Override
    public int count() {
        String sql = "select count(*) from t_user";
        return jt.queryForObject(sql, Integer.class);
    }


    @Override
    public User get(String id) {
        String sql = "select t.id, t.name from t_user t where t.id=? ";
        return jt.query(sql, new Object[]{id}, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                /**
                 * 查询到的结果集游标位于第0条记录处
                 * 如果结果集为空，则rs.next()返回false，此时返回null
                 * 如果结果集不为空，而由于在业务逻辑上确保只会有一条记录，则此时可以通过rs.next()将游标移动到第1条记录处，并完成结果遍历
                 */
                if (rs.next()) {
                    return rsToUser(rs);
                } else {
                    return null;
                }
            }
        });
    }


    @Deprecated
    @Override
    public User get2(String id) {
        String sql = "select * from t_user t where t.id=? ";
        try {
            return jt.queryForObject(sql, new Object[]{id}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    /**
                     * queryForObject对自动将结果集的游标置于第一行。
                     * 因此，当查询结果集为一个时，则不需要通过rs.next()来取值
                     * 但是，当查询结果集为空时，会报EmptyResultDataAccessException异常，只有通过捕捉异常才能够确定结果为空返回null
                     * 建议使用query(..)方法替代queryForObject方法
                     */
                    return rsToUser(rs);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public String getNameById(String id) {
        String sql = "select t.name from t_user t where t.id=? ";
        try {
            return jt.queryForObject(sql, String.class, id);
        } catch (EmptyResultDataAccessException e) {
            // 当查询结果集为空时，只有通过捕捉异常才能确定结果集为空，这种编程方式不是很友好
            return null;
        }
    }


    @Override
    public List<User> listByStatus(int status) {
        String sql = "select * from t_user t where t.status=? ";
        return jt.query(sql, new Object[]{status}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rsToUser(rs);
            }
        });
    }


    @Override
    public List<String> listNamesByStatus(int status) {
        String sql = "select DISTINCT t.name from t_user t where t.status=? ";
        return jt.queryForList(sql, String.class, status);
    }


    /**
     * ResultSet转成User实体对象
     *
     * @param rs 查询到的结果集
     * @return 由结果集转换的实体User对象
     */
    private User rsToUser(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPwd(rs.getString("pwd"));
            user.setStatus(rs.getInt("status"));
            user.setCreateTime(rs.getDate("create_time"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}

