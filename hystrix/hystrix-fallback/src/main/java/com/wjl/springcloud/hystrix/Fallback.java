package com.wjl.springcloud.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wjl.springcloud.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangJiaLun
 * @date 2022-03-09
 **/
@Slf4j
@Component
public class Fallback implements MyService {

    @Override
    @HystrixCommand(fallbackMethod = "fallback2")
    public String error() {
        log.error("服务调用失败");
        throw new RuntimeException("first fallback");
    }

    @HystrixCommand(fallbackMethod = "fallback3")
    public String fallback2(){
        log.info("fallback again 2");
        throw new RuntimeException("fallback again2");
    }

    public String fallback3(){
        log.info("fallback again 3");
        return "success";
    }

    @Override
    public String retry(@RequestParam(name = "timeout") Integer timeout) {
        return "late";
    }

    @Override
    public String sayHi() {
        return null;
    }


}
