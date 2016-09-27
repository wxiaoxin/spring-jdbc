package com.xx.spring.jdbc.dao;

import com.xx.spring.jdbc.dao.impl.UserDao;
import com.xx.spring.jdbc.entity.User;
import com.xx.spring.jdbc.util.UUIDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wxiao on 2016.9.19.
 *
 * UserDao测试类
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSave() {
        User user = new User();
        user.setId(UUIDGenerator.randomUUID());
        user.setName("zhangsan");
        user.setPwd("123456");
        user.setStatus(0);
        user.setCreateTime(new Date());
        int result = userDao.save(user);
        System.out.println(result);
    }


    @Test
    public void testBatchSave() {
        User user1 = new User();
        user1.setId(UUIDGenerator.randomUUID());
        user1.setName("aa");
        user1.setPwd("aa");
        user1.setStatus(1);
        user1.setCreateTime(new Date());

        User user2 = new User();
        user2.setId(UUIDGenerator.randomUUID());
        user2.setName("bb");
        user2.setPwd("bb");
        user2.setStatus(0);
        user2.setCreateTime(new Date());

        User user3 = new User();
        user3.setId(UUIDGenerator.randomUUID());
        user3.setName("cc");
        user3.setPwd("cc");
        user3.setStatus(1);
        user3.setCreateTime(new Date());

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        int[] ints = userDao.batchSave(userList);
        for (int i : ints ) {
            System.out.println(i);
        }
    }

    @Test
    public void testDel() {
        User user = new User();
        user.setId("3080e6b2ef6741daac217e147db880ca");
        int result = userDao.del(user);
        System.out.println(result);
    }


    @Test
    public void testUpdate() {
        User user = new User();
        user.setId("51c32381ad9a4329b7efe8269926a696");
        user.setName("aa");
        user.setPwd("aa");
        user.setStatus(1);
        int result = userDao.update(user);
        System.out.println(result);
    }


    @Test
    public void testBatchUpdate() {
        User user1 = new User();
        user1.setId("51c32381ad9a4329b7efe8269926a696");
        user1.setName("aa");
        user1.setPwd("aa");
        user1.setStatus(1);
        user1.setCreateTime(new Date());

        User user2 = new User();
        user2.setId("566613b45c13460697de8c8f649f0d4a");
        user2.setName("bb");
        user2.setPwd("bb");
        user2.setStatus(0);
        user2.setCreateTime(new Date());

        User user3 = new User();
        user3.setId("cdb0b10627e745a7989aab283c1eba8c");
        user3.setName("cc");
        user3.setPwd("cc");
        user3.setStatus(1);
        user3.setCreateTime(new Date());

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        int[] ints = userDao.batchUpdate(userList);
        for(int i : ints) {
            System.out.println(i);
        }

    }

    @Test
    public void testCount() {
        int count = userDao.count();
        System.out.println("记录总数：" + count);
    }


    @Test
    public void testGet() {
        String id = "3080e6b2ef6741daac217e147db880ca";
        User user = userDao.get(id);
        System.out.println(user);
    }


    @Test
    public void testGet2() {
        String id = "3080e6b2ef6741daac217e147db880ca";
        User user = userDao.get2(id);
        System.out.println(user);
    }


    @Test
    public void testGetNameById() {
        String id = "3080e6b2ef6741daac217e147db880ca";
        String name = userDao.getNameById(id);
        System.out.println(name);
    }


    @Test
    public void testListByStatus() {
        List<User> users = userDao.listByStatus(0);
        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void testListNamesByStatus() {
        List<String> nameList = userDao.listNamesByStatus(0);
        System.out.println(nameList);
    }


}