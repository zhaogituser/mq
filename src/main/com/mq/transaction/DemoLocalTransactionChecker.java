package com.mq.transaction;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
/**
 * Created by ${zhao} on 2017/11/3 0003.
 */
public class DemoLocalTransactionChecker implements LocalTransactionChecker {


    @Override
    public TransactionStatus check(Message message) {
        System.out.println("开始回查本地事物状态");
        return TransactionStatus.CommitTransaction;
    }
}
