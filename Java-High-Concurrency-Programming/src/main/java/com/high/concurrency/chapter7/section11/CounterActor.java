package com.high.concurrency.chapter7.section11;

import akka.actor.UntypedActor;
import akka.dispatch.Mapper;
import scala.concurrent.Future;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/07 下午8:56
 */
public class CounterActor extends UntypedActor {

    Mapper addMapper = new Mapper<Integer, Integer>() {
        @Override
        public Integer apply(Integer i) {
            return i + 1;
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Integer) {
            for (int i = 0; i < 10000; i++) {
                Future<Integer> f = AgentDemo.counterAgent.alter(addMapper);
                AgentDemo.futures.add(f);
            }
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }
    }
}
