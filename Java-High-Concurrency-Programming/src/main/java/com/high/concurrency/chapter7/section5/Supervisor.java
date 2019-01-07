package com.high.concurrency.chapter7.section5;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/07 上午11:39
 */
public class Supervisor extends UntypedActor {

    private static SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.create(1, TimeUnit.SECONDS), new Function<Throwable, SupervisorStrategy.Directive>() {
        @Override
        public SupervisorStrategy.Directive apply(Throwable t) throws Exception {
            if (t instanceof ArithmeticException) {
                System.out.println("meet ArithmeticException, just resume");
                return SupervisorStrategy.resume();
            } else if (t instanceof NullPointerException) {
                System.out.println("meet NullPointerException, just restart");
                return SupervisorStrategy.restart();
            } else if (t instanceof IllegalAccessException) {
                return SupervisorStrategy.stop();
            } else {
                return SupervisorStrategy.escalate();
            }
        }
    });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof Props) {
            getContext().actorOf((Props)o, "restartActor");
        } else {
            unhandled(o);
        }
    }
}
