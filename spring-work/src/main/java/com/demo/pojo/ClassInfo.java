package com.demo.pojo;

import java.util.List;

public class ClassInfo {

    /**
     * 类全路径
     */
    private String className;
    /**
     * 利用反射生成的对象实例
     */
    private Object obj;
    /**
     * 存对象的map的key
     */
    private String key;

    /**
     * 实现的接口
     */
    private Class<?>[] interfaces;
    /**
     * 是否带有@Service注解
     */
    private boolean haveService;
    /**
     * 是否带有@Transactional注解
     */
    private boolean haveTransactional;

    /**
     * @Transactional注解值
     */
    private boolean transactional;
    /**
     * 属性
     */
    private List<AnnoField> fieldList;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class<?>[] interfaces) {
        this.interfaces = interfaces;
    }

    public boolean isHaveService() {
        return haveService;
    }

    public void setHaveService(boolean haveService) {
        this.haveService = haveService;
    }

    public boolean isHaveTransactional() {
        return haveTransactional;
    }

    public void setHaveTransactional(boolean haveTransactional) {
        this.haveTransactional = haveTransactional;
    }

    public boolean isTransactional() {
        return transactional;
    }

    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }

    public List<AnnoField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<AnnoField> fieldList) {
        this.fieldList = fieldList;
    }
}
