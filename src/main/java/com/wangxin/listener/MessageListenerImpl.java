package com.wangxin.listener;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;


public class MessageListenerImpl implements MessageListenerConcurrently {

    private static final Logger log = LoggerFactory.getLogger(MessageListenerImpl.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt messageExt : msgs) {
            log.info("# {}", messageExt);
            try {
                String body = new String(messageExt.getBody(), "UTF-8");
                log.info("# {}", body);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //System.out.println(messageExt.toString());
            //System.out.println(new String(messageExt.getBody()));
        }
        log.info("# getDelayLevelWhenNextConsume={} , getMessageQueue={} , getDelayLevelWhenNextConsume={}", context.getDelayLevelWhenNextConsume(), context.getMessageQueue(), context.getDelayLevelWhenNextConsume());
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}