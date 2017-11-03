package mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ${zhao} on 2017/11/3 0003.
 */
public class ProducerWithSpring {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/producer.xml");
        Producer producer = (Producer) applicationContext.getBean("producer");
        for (int i=0;i<100;i++) {
            Message message = new Message(
                    // Message所属的Topic
                    "TopicTestMQ",
                    // Message Tag 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
                    "TagA",
                    // Message Body 可以是任何二进制形式的数据， MQ不做任何干预
                    // 需要Producer与Consumer协商好一致的序列化和反序列化方式
                    "你好".getBytes());
            // 设置代表消息的业务关键属性，请尽可能全局唯一
            // 以方便您在无法正常收到消息情况下，可通过MQ 控制台查询消息并补发
            // 注意：不设置也不会影响消息正常收发
            message.setKey("MQID_01");
            // 发送消息，只要不抛异常就是成功
            try {
                SendResult sendResult = producer.send(message);
                assert sendResult!= null;
                System.out.println("发送成功:"+sendResult.getMessageId());
            } catch (ONSClientException e) {
                System.out.println("发送失败");

            }

        }

    }
}
