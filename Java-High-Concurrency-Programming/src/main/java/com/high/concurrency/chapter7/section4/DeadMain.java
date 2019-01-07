package com.high.concurrency.chapter7.section4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/07 上午10:53
 */
public class DeadMain {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("deadwatch", ConfigFactory.load("samplehello.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        worker.tell(MyWorker.Msg.WORKING, ActorRef.noSender());
        worker.tell(MyWorker.Msg.DONE, ActorRef.noSender());
        worker.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}
