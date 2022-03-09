package com.wjl.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangJiaLun
 * @date 2022-03-08
 **/
@FeignClient("feign-client")
public interface IService {

    @GetMapping("/sayHi")
    public String sayHi();

    @GetMapping("/retry")
    public String retry(@RequestParam(name = "timeout") Integer timeout);
}
