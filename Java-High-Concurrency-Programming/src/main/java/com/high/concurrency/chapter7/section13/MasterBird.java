package com.high.concurrency.chapter7.section13;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/08 下午2:04
 */
public class MasterBird extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private PosValue gBest = null;

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof PBBestMsg) {
            PosValue pBest = ((PBBestMsg)msg).getValue();
            if (gBest == null || gBest.value < pBest.value) {
                System.out.println(msg + "\n");
                gBest = pBest;
                ActorSelection selection = getContext().actorSelection("/user/bird_*");
                selection.tell(new GBestMsg(gBest), getSelf());
            }
        } else {
            unhandled(msg);
        }
    }
}
