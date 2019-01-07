package com.high.concurrency.chapter7.section10;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.high.concurrency.chapter7.section4.MyWorker;
import com.high.concurrency.chapter7.section4.WatchActor;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/07 下午5:38
 */
public class AskMain {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("askdemo", ConfigFactory.load("samplehello.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        ActorRef printer = system.actorOf(Props.create(Printer.class), "printer");
        system.actorOf(Props.create(WatchActor.class, worker), "watcher");
        Future<Object> f = null;
        int re = 0;//Await.result(f, Duration.create(6, TimeUnit.SECONDS));
        System.out.println("return : " + re);
        f = null;
        //pipe(f, system.dispatcher())
        worker.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}
