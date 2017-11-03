package mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ${zhao} on 2017/11/3 0003.
 */
public class ProducerTransMsgWithSpring {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("transactionProducer.xml");
        TransactionProducer transactionProducer = (TransactionProducer) applicationContext.getBean("transactionProducer");
        Message message = new Message("TransTest", "TagA", "欢迎".getBytes());

        SendResult sendResult = transactionProducer.send(message, (msg, arg) -> {
            System.out.println("执行本地事务");
            return TransactionStatus.CommitTransaction;//根据本地事务执行结果来返回不同的TransactionStatus
        }, null);
        /*  lambda expression 
        SendResult sendResult1 = transactionProducer.send(message, new LocalTransactionExecuter() {
            @Override
            public TransactionStatus execute(Message msg, Object arg) {
                System.out.println("执行本地事务");
                return TransactionStatus.CommitTransaction;//根据本地事务执行结果来返回不同的TransactionStatus
            }
        }, null);*/

    }
}
