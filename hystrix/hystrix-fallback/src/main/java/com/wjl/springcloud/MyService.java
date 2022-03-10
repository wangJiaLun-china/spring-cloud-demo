package com.wjl.springcloud;

import com.wjl.springcloud.hystrix.Fallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wangJiaLun
 * @date 2022-03-09
 **/
@FeignClient(name = "feign-client", fallback = Fallback.class)
public interface MyService  extends IService{

}
