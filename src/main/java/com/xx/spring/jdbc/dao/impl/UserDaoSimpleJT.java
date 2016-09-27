package com.xx.spring.jdbc.dao.impl;

import com.xx.spring.jdbc.dao.IUserDao;
import com.xx.spring.jdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/27.
 *
 * 采用SimpleJdbc类实现
 *
 */

@Repository
public class UserDaoSimpleJT implements IUserDao {


    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setSimpleJdbcInsert(DataSource dataSource) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("t_user");
    }

    @Override
    public int save(User user) {

//        Map<String, Object> params = new HashMap<>();
//        params.put("id", user.getId());
//        params.put("name", user.getName());
//        params.put("pwd", user.getPwd());
//        params.put("status", user.getStatus());
//        params.put("create_time", user.getCreateTime());
//        return simpleJdbcInsert.execute(params);

        // 要求实体类的属性名与数据表的字段名称完全一致，不灵活
//        SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
//        return simpleJdbcInsert.execute(parameters);

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("pwd", user.getPwd())
                .addValue("status", user.getStatus())
                .addValue("create_time", user.getCreateTime());
        return simpleJdbcInsert.execute(parameters);

    }

    @Override
    public int[] batchSave(List<User> userList) {
        return new int[0];
    }

    @Override
    public int del(User user) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }


    @Override
    public int[] batchUpdate(List<User> userList) {
        return new int[0];
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public User get2(String id) {
        return null;
    }

    @Override
    public String getNameById(String id) {
        return null;
    }

    @Override
    public List<User> listByStatus(int status) {
        return null;
    }

    @Override
    public List<String> listNamesByStatus(int status) {
        return null;
    }
}
