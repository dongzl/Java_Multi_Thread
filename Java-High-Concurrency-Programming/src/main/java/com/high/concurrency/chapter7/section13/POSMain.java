package com.high.concurrency.chapter7.section13;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/08 下午2:08
 */
public class POSMain {

    public static final int BIRD_COUNT = 100000;

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("posSystem", ConfigFactory.load("samplehello.conf"));
        system.actorOf(Props.create(MasterBird.class), "masterbird");
        for (int i = 0; i < BIRD_COUNT; i++) {
            system.actorOf(Props.create(Bird.class), "bird_" + i);
        }
    }
}
