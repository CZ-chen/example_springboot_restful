package tech.nodex.example_springboot_restful.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tech.nodex.example_springboot_restful.App;
import tech.nodex.example_springboot_restful.service.ExampleService;

import javax.validation.ConstraintViolationException;

@SpringBootTest
@ContextConfiguration(classes=App.class)
@RunWith(SpringRunner.class)
public class ExampleTest {

    @Autowired
    ExampleService exampleService;

    @Test
    public void test1(){
        try {
            exampleService.doSomething("abc");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ConstraintViolationException);
        }
    }

    @Test
    public void test2(){
        String email = "chenzhao@rongcapital.cn";
        Assert.assertEquals(email,exampleService.doSomething(email));
        exampleService.doSomething("chenzhao@rongcapital.cn");
    }

}
