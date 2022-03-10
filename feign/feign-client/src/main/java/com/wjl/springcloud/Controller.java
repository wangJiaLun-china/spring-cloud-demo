package com.wjl.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangJiaLun
 * @date 2022-03-08
 **/
@Slf4j
@RestController
public class Controller implements IService{

    @Value("${server.port}")
    private String port;

    @Override
    public String sayHi(){
        return "This is " + port;
    }

    @Override
    public String retry(@RequestParam("timeout") Integer timeout) {
        while (timeout-- >=0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("retry");
        return port;
    }

    @Override
    public String error() {
        throw new RuntimeException("error");
    }
}
