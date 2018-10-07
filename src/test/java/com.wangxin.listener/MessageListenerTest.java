package com.wangxin.listener;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 王鑫
 * @date 2018-10-07 09:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml", "classpath:spring/spring-rocketmq.xml"})
public class MessageListenerTest {

    private static final Logger log = LoggerFactory.getLogger(MessageListenerTest.class);

    @Qualifier("rocketmqProduct")
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    /**
     * 往RocketMQ发送消息
     *
     * @author 王鑫
     * @date 2018-10-07 14:11
     */
    @Test
    public void sendMessage() {
        try {

            String topic = "PushTopic";
            String tags = "push";
            String body = "rocketmq for test";

            Message msg = new Message(topic, tags, body.getBytes());
            SendResult result = defaultMQProducer.send(msg);
            log.info("# id={} , result={}", result.getMsgId(), result.getSendStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
