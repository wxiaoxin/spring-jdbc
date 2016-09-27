package com.xx.spring.jdbc.dao.impl;

import com.xx.spring.jdbc.base.BaseDao;
import com.xx.spring.jdbc.dao.IUserDao;
import com.xx.spring.jdbc.entity.User;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */

@Repository
public class UserDaoNamedJT extends BaseDao implements IUserDao {

    @Override
    public int save(User user) {
        String sql = "insert into t_user values()";
        return 0;
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

        String namedSql = "update t_user set name=:name, pwd=:pwd, status=:status, create_time=:create_time where id=:id";

        // 使用NamedJdbcTemple实现批处理，不过，该方法要求实体类属性与数据表的字段名称保持完全一致
        // 由于数据表t_user的字段create_time与User类的createTime属性名称不一致，因此该批处理无法执行
        SqlParameterSource[] parameterSources = SqlParameterSourceUtils.createBatch(userList.toArray());
        return nJt.batchUpdate(namedSql, parameterSources);
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
