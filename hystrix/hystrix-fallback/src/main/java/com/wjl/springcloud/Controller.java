package com.wjl.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangJiaLun
 * @date 2022-03-09
 **/
@Slf4j
@RestController
public class Controller {

    @Autowired
    private MyService myService;

    @GetMapping("fallback")
    public String fallback(){
        return myService.error();
    }

    @GetMapping("timeout")
    public String timeout(Integer timeout){
        return myService.retry(timeout);
    }

    @GetMapping("timeout2")
    @HystrixCommand(
            fallbackMethod = "timeoutFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
    )
    public String timeout2(Integer timeout){
        return myService.retry(timeout);
    }

    public String timeoutFallback(Integer timeout){
        return "success";
    }
}
