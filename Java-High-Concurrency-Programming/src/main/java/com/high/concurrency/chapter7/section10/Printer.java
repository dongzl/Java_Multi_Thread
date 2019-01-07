package com.high.concurrency.chapter7.section10;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.high.concurrency.chapter7.section2.Greeter;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/07 下午5:45
 */
public class Printer extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public static enum Msg {
        DONE, CLOSE;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Integer) {
            System.out.println("Printer: " + msg);
        }
        if (msg == Msg.DONE) {
            log.info("Stop Working");
        }
        if (msg == Msg.CLOSE) {
            log.info("I will shutdown");
            getSender().tell(Msg.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }
    }
}
