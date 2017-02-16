package tech.nodex.example_springboot_restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.filter.CharacterEncodingFilter;
import tech.nodex.example_springboot_restful.dao.utils.ActiveRecordConfig;

/**
 * Spring Boot 项目启动器
 * Created by cz on 2017-1-15.
 */
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class App {
    private static final Logger loogger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        loogger.info("#######################  APP Starting ... ################################");
        SpringApplication.run(App.class, args);
        loogger.info("#######################  APP Success Started . ################################");
    }

    /**-
     * 初始化编码连接器
     * @return
     */
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        loogger.info("#######################  Init CharacterEncodingFilter ... ################################");
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceRequestEncoding(true);
        filter.setForceResponseEncoding(true);
        return filter;
    }

    /**
     * 初始化JFinal Active Record
     * @return
     */
    @Bean
    public ActiveRecordConfig activeRecord() {
        ActiveRecordConfig activeRecordConfig
                = new ActiveRecordConfig(
                    "jdbc:mysql://your.mysql.database:3306/example",
                    "example",
                    "Example123");
        activeRecordConfig.connect();
        return activeRecordConfig;
    }

    /**
     * 用于validation
     * 官方文档: <a>http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#validation-beanvalidation-spring</a>
     * @return
     */
    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        //在这里，你可以指定更多的校验配置细节
        return localValidatorFactoryBean;
    }

    /**
     * 用于validation
     * 官方文档: <a>http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#validation-beanvalidation-spring</a>
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }
}
