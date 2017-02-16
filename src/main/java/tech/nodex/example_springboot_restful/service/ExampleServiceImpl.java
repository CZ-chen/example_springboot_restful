package tech.nodex.example_springboot_restful.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 这个类仅用来演示Spring测试中的依赖注入和参数Validation
 * Created by cz on 2017-2-16.
 */
@Service
public class ExampleServiceImpl implements ExampleService {
    @Override
    public String doSomething(String value) {
        return value;
    }
}
