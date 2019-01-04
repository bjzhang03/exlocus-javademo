package com.bjzhang.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@Configuration
public class Applications {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "hello")
    @ResponseBody
    public String hello() {
        logger.info("test");
        return "hello spring boot";
    }

    public static void main(String[] args) {
        SpringApplication.run(Applications.class, args);
    }
}
