package com.high.concurrency.chapter7.section2;

import akka.actor.UntypedActor;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/07 上午9:12
 */
public class Greeter extends UntypedActor {

    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg == Msg.GREET) {
            System.out.println("Hello world!");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(msg);
        }
    }
}
