package com.unionman.springbootelasticsearchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zxp.esclientrhl.annotation.EnableESTools;

@SpringBootApplication
@EnableESTools
public class SpringbootElasticsearchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootElasticsearchDemoApplication.class, args);
    }

}
