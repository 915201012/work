package com.demo.sqlsession;

/**
 * @author user
 */
public interface SqlSessionFactory {
    /**
     * 获取会话
     *
     * @return
     */
    SqlSession openSession();
}
