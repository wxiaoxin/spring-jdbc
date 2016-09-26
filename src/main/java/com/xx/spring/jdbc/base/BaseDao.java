package com.xx.spring.jdbc.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2016/9/20.
 *
 * 对JdbcTemplate NamedParameterJdbcTemplate封装
 *
 */

public class BaseDao {

    protected JdbcTemplate jt;

    protected NamedParameterJdbcTemplate nJt;

    @Autowired
    public void setJt(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }

    @Autowired
    public void setnJt(DataSource dataSource) {
        nJt = new NamedParameterJdbcTemplate(dataSource);
    }

}
