package com.xx.spring.jdbc.dao;

import com.xx.spring.jdbc.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 *
 * 用户User操作类接口
 *
 */
public interface IUserDao {

    /**
     * 保存用户对象
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 批量保存用户对象
     * @param userList
     * @return
     */
    int[] batchSave(List<User> userList);

    /**
     * 删除用户对象
     * @param user 将要删除的对象
     * @return
     */
    int del(User user);

    /**
     * 更新
     * @param user 将要被更新的对象
     * @return
     */
    int update(User user);


    /**
     * 批处理更新
     * @param userList 将要更新的User列表
     * @return
     */
    int[] batchUpdate(List<User> userList);

    /**
     * 获取记录总数
     * @return 记录的个数;如果记录为空则返回0
     */
    int count();

    /**
     * 获取指定id的User对象
     * @param id 用户的主键id
     * @return 查询到的用户对象。如果查询结果为空，则返回null
     */
    User get(String id);


    /**
     * 获取指定id的User对象
     * @param id 用户的主键id
     * @return 查询到的用户对象。如果查询结果为空，则返回null
     */
    User get2(String id);

    /**
     * 根据用户的id查询用户名
     * @param id 用户id
     * @return 用户名。如果查询结果为空则返回null
     */
    String getNameById(String id);

    /**
     * 根据状态字段查询用户列表
     * @param status 状态码
     * @return 用户列表
     */
    List<User> listByStatus(int status);

    /**
     * 根据状态值查询所有用户的姓名列表
     * @param status 状态码
     * @return
     */
    List<String> listNamesByStatus(int status);

}
