package com.wjl.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wangJiaLun
 * @date 2022-03-08
 **/
@FeignClient("eureka-client")
public interface IService {

    @GetMapping("/sayHi")
    String sayHi();
}
