package com.demo.config;

import com.demo.util.ParameterMapping;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author user
 */
public class BoundSql {
    private String sqlTest;
    private List<ParameterMapping> parameterMappingList = Lists.newArrayList();

    public BoundSql(String sqlTest, List<ParameterMapping> parameterMappingList) {
        this.sqlTest = sqlTest;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlTest() {
        return sqlTest;
    }

    public void setSqlTest(String sqlTest) {
        this.sqlTest = sqlTest;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
