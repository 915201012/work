package com.demo.sqlsession;

import com.demo.pojo.Configuration;
import com.demo.pojo.MappedStatement;

import java.util.List;

/**
 * @author user
 */
public interface Executor {
    /**
     * 查询
     *
     * @param configuration
     * @param statement
     * @param args
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> List<E> query(Configuration configuration, MappedStatement statement, Object... args) throws Exception;

    /**
     * 新增
     *
     * @param configuration
     * @param statement
     * @param args
     */
    void save(Configuration configuration, MappedStatement statement, Object... args) throws Exception;

    /**
     * 修改
     *
     * @param configuration
     * @param statement
     * @param args
     * @throws Exception
     */
    void update(Configuration configuration, MappedStatement statement, Object... args) throws Exception;

    /**
     * 删除
     *
     * @param configuration
     * @param statement
     * @param args
     * @throws Exception
     */
    void delete(Configuration configuration, MappedStatement statement, Object... args) throws Exception;
}
