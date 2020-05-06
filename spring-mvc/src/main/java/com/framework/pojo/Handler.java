package com.framework.pojo;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Handler implements Serializable {

    private Object clazz;
    private Method method;
    private Pattern pattern;
    private Map<String, Integer> paramMapping;
    private List<String> limits;

    public Handler(Object clazz, Method method, Pattern pattern) {
        this.clazz = clazz;
        this.method = method;
        this.pattern = pattern;
        paramMapping = new HashMap<>();
        limits = new ArrayList<>();
    }

    public Object getClazz() {
        return clazz;
    }

    public void setClazz(Object clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Map<String, Integer> getParamMapping() {
        return paramMapping;
    }

    public void setParamMapping(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    public List<String> getLimits() {
        return limits;
    }

    public void setLimits(List<String> limits) {
        this.limits = limits;
    }
}
