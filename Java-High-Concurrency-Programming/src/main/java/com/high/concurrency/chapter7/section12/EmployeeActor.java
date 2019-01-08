package com.high.concurrency.chapter7.section12;

import akka.actor.UntypedActor;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/08 上午9:59
 */
public class EmployeeActor extends UntypedActor {

    private Ref.View<Integer> count = STM.newRef(50);

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Coordinated) {
            final Coordinated c = (Coordinated) msg;
            final int downCount = (Integer)c.getMessage();
            try {
                c.atomic(new Runnable() {
                    @Override
                    public void run() {
                        STM.increment(count, downCount);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("GetCount".equals(msg)) {
            getSender().tell(count.get(), getSelf());
        } else {
            unhandled(msg);
        }
    }
}
