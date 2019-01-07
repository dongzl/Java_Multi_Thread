package com.high.concurrency.chapter7.section9;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import com.high.concurrency.chapter7.section4.WatchActor;
import com.typesafe.config.ConfigFactory;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/07 下午2:49
 */
public class BabyActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public static enum Msg {
        SLEEP, PLAY, CLOSE;
    }

    Procedure<Object> angry = new Procedure<Object>() {
        @Override
        public void apply(Object message) throws Exception {
            System.out.println("angryBaby:" + message);
            if (message == Msg.SLEEP) {
                getSender().tell("I am already angry", getSelf());
                System.out.println("I am already angry");
            } else if (message == Msg.PLAY) {
                System.out.println("I like playing");
                getContext().become(happy);
            }
        }
    };

    Procedure<Object> happy = new Procedure<Object>() {
        @Override
        public void apply(Object message) throws Exception {
            System.out.println("happyApply:" + message);
            if (message == Msg.PLAY) {
                getSender().tell("I am already happy: -)", getSelf());
                System.out.println("I am already happy: -)");
            } else if (message == Msg.SLEEP) {
                System.out.println("I don't want to sleep");
                getContext().become(angry);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        System.out.println("onReceive:" + msg);
        if (msg == Msg.SLEEP) {
            getContext().become(angry);
        } else if (msg == Msg.PLAY) {
            getContext().become(happy);
        } else {
            unhandled(msg);
        }
    }

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("become", ConfigFactory.load("samplehello.conf"));
        ActorRef child = system.actorOf(Props.create(BabyActor.class), "baby");
        system.actorOf(Props.create(WatchActor.class, child), "watcher");
        child.tell(Msg.PLAY, ActorRef.noSender());
        child.tell(Msg.SLEEP, ActorRef.noSender());
        child.tell(Msg.PLAY, ActorRef.noSender());
        child.tell(Msg.PLAY, ActorRef.noSender());
        child.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}
