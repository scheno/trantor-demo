package io.terminus.emp.camunda;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenchen
 * @version 1.0
 * @date 2021/8/15 6:38 下午
 */
@SpringBootApplication
//Camunda配置注解
@EnableProcessApplication
public class CamundaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamundaApplication.class, args);
    }

}