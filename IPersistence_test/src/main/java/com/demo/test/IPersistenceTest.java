package com.demo.test;

import com.demo.io.Resources;
import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import com.demo.sqlsession.SqlSession;
import com.demo.sqlsession.SqlSessionFactory;
import com.demo.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


/**
 * @author user
 */
public class IPersistenceTest {

    private UserMapper userMapper;

    @Before
    public void before() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    /**
     * 查询所有
     */
    @Test
    public void test1() {
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

    /**
     * 条件查询
     */
    @Test
    public void test2() {
        User user = new User();
        user.setId(new Long(1));
        User user1 = userMapper.findByCondition(user);
        System.out.println(user1);
    }

    /**
     * 新增
     */
    @Test
    public void test3() {
        User user = new User();
        user.setId(new Long(7));
        user.setName("jay");
        user.setAge(24);
        user.setAddress("中国台湾");
        userMapper.saveUser(user);
    }

    /**
     * 修改
     */
    @Test
    public void test4() {
        User user = new User();
        user.setId(new Long(7));
        user.setName("jolin");
        user.setAge(21);
        user.setAddress("中国台湾");
        userMapper.updateUser(user);
    }

    /**
     * 删除
     */
    @Test
    public void test5() {
        User user = new User();
        user.setId(new Long(7));
        userMapper.deleteUser(user);
    }

}
