package com.demo.sqlsession;

import java.util.List;

/**
 * @author user
 */
public interface SqlSession {
    /**
     * 查询所有
     *
     * @param statementId
     * @param args
     * @return
     * @throws Exception
     */
    <E> List<E> selectList(String statementId, Object... args) throws Exception;

    /**
     * 条件查询
     *
     * @param statementId
     * @param args
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T selectOne(String statementId, Object... args) throws Exception;

    /**
     * 为dao接口生成代理类
     *
     * @param mapperClass
     * @param <T>
     * @return
     */

    <T> T getMapper(Class<?> mapperClass);

    /**
     * 新增
     *
     * @param statementId
     * @param args
     * @throws Exception
     */
    void save(String statementId, Object... args) throws Exception;

    /**
     * 修改
     *
     * @param statementId
     * @param args
     * @throws Exception
     */
    void update(String statementId, Object... args) throws Exception;

    /**
     * 删除
     *
     * @param statementId
     * @param args
     * @throws Exception
     */
    void delete(String statementId, Object... args) throws Exception;
}
