package com.high.concurrency.chapter7.section5;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.typesafe.config.ConfigFactory;
import scala.Option;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/07 上午11:49
 */
public class RestartActor extends UntypedActor {

    public enum Msg {
        DONE, RESTART;
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("preStart hashCode:" + this.hashCode());
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("postStop hashCode:" + this.hashCode());
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("preRestart hashCode:" + this.hashCode());
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println("postRestart hashCode:" + this.hashCode());
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg == Msg.DONE) {
            getContext().stop(getSelf());
        } else if (msg == Msg.RESTART) {
            System.out.println(((Object)null).toString());
            double a = 0 / 0;
        }
        unhandled(msg);
    }

    public static void customStrategy(ActorSystem system) {
        ActorRef a = system.actorOf(Props.create(Supervisor.class), "Supervisor");
        a.tell(Props.create(RestartActor.class), ActorRef.noSender());
        ActorSelection sel = system.actorSelection("akka://lifecycle/user/Supervisor/restartActor");
        for (int i = 0; i < 100; i++) {
            sel.tell(Msg.RESTART, ActorRef.noSender());
        }
    }

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("lifecycle", ConfigFactory.load("lifecycle.conf"));
        customStrategy(system);
    }
}
