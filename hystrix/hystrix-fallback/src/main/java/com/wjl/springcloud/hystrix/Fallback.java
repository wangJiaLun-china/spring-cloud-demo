package com.wjl.springcloud.hystrix;

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
    public String error() {
        log.error("服务调用失败");
        return "123456";
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
