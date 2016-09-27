package com.xx.spring.jdbc.dao.impl;

import com.xx.spring.jdbc.entity.User;
import com.xx.spring.jdbc.util.UUIDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/27.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDaoWithSimpleJTTest {

    @Autowired
    private UserDaoSimpleJT userDao;

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setId(UUIDGenerator.randomUUID());
        user.setName("zhangsan");
        user.setPwd("123456");
        user.setStatus(0);
        user.setCreateTime(new Date());
        int result = userDao.save(user);
        System.out.println(result);
    }
}