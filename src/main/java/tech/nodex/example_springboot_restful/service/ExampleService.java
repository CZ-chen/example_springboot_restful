package tech.nodex.example_springboot_restful.service;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

/**
 * 这个类仅用来演示Spring测试中的依赖注入和参数Validation
 * Created by cz on 2017-2-16.
 */
@Validated
public interface ExampleService {
    String doSomething(@NotNull @Email String value);
}
