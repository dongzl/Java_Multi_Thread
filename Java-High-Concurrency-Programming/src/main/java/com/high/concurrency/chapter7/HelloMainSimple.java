package com.high.concurrency.chapter7;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/07 上午9:19
 */
public class HelloMainSimple {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("samplehello.conf"));
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloworld");
        System.out.println("HelloWorld Actor Path:" + a.path());
    }
}
