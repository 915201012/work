package com.demo.service;

import com.framework.annotations.Service;

@Service
public class DemoServiceImpl implements IDemoService {
    @Override
    public String get(String name) {

        System.out.println("service 实现类中的name参数：" + name);
        return name;
    }
}
