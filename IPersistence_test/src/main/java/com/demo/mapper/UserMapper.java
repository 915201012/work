package com.demo.mapper;

import com.demo.pojo.User;

import java.util.List;

/**
 * @author user
 */
public interface UserMapper {

    /**
     * 查询所有
     *
     * @return
     */
    List<User> findAll();


    /**
     * 根据条件查询
     *
     * @param user
     * @return
     */
    User findByCondition(User user);

    /**
     * 新增
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 修改
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除
     *
     * @param user
     */
    void deleteUser(User user);
}
