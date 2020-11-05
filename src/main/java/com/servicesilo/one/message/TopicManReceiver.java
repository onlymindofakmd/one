package com.servicesilo.one.message;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@Slf4j
public class TopicManReceiver {

    //自动确认
    //@RabbitListener(queues = "QueueA")
    //public void process(JSONObject obj) {
    //    System.out.println("TopicReceiver Queue-Name-A  : " + obj.toString());
    //}

    //手动确认
    @RabbitListener(queues = "QueueA")
    public void receiveMessage01(Object msg, Channel channel, Message message) throws IOException {
        try {
            System.out.println("Message: "+message.getMessageProperties().toString());
            System.out.println("Redelivered: "+ message.getMessageProperties().getDeliveryTag());
            // 这里模拟一个空指针异常，
            String string = null;
            string.length();

            log.info("【Consumer01成功接收到消息】>>> {}", msg);
            // 手动-成功确认  param1-deliveryTag-单调递增正整数，此条消息的唯一ID  param2-false-确认单条-true-一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {

            if (message.getMessageProperties().getRedelivered()) {
                log.info("【Consumer01】消息已经回滚过，拒绝接收消息 ： {}", msg);
                // 拒绝消息，并且不再重新进入队列
                //public void basicReject(long deliveryTag, boolean requeue)
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.info("【Consumer01】消息即将返回队列重新处理 ：{}", msg);
                //设置消息重新回到队列处理
                // requeue表示是否重新回到队列，true重新入队
                //public void basicNack(long deliveryTag, boolean multiple, boolean requeue)
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            e.printStackTrace();
        }
    }
}
