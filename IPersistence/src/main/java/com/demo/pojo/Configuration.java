package com.demo.pojo;

import com.google.common.collect.Maps;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author user
 */
public class Configuration {

    private DataSource dataSource;
    private Map<String, MappedStatement> mappedStatementMap = Maps.newHashMap();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
