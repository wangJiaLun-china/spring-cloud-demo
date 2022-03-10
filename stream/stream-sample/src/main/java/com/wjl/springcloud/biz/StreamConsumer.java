package com.wjl.springcloud.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author wangJiaLun
 * @date 2022-03-10
 **/
@Slf4j
@EnableBinding(
        value = {
                Sink.class,
                // MyTopic.class
        }
)
public class StreamConsumer {

    @StreamListener(Sink.INPUT)
    public void consume(Object payload){
        log.info("message consumerd successfully, payload= {}", payload);
    }
}
