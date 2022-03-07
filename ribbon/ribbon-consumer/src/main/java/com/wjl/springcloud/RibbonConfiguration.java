package com.wjl.springcloud;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangJiaLun
 * @date 2022-03-07
 **/
@Configuration
// 基于注解配置负载均衡策略, 优先级大于配置文件
@RibbonClient(name = "eureka-client", configuration = com.netflix.loadbalancer.RoundRobinRule.class)
public class RibbonConfiguration {

    // 基于注入Bean配置负载均衡策略
//    @Bean
    public IRule defaultLBStrategy(){
        return new RandomRule();
    }
}
