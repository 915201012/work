package com.demo.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author user
 */
public class ParameterMappingTokenHandler implements TokenHandler{

    private List<ParameterMapping> parameterMappings = Lists.newArrayList();

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    @Override
    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content));
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        ParameterMapping parameterMapping = new ParameterMapping(content);
        return parameterMapping;
    }

}
