package com.high.concurrency.chapter7.section12;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.transactor.Coordinated;
import akka.util.Timeout;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Await;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/08 上午9:31
 */
public class STMDemo {

    public static ActorRef company = null;

    public static ActorRef employee = null;

    public static void main(String[] args) throws Exception {
        final ActorSystem system = ActorSystem.create("transactionDemo", ConfigFactory.load("samplehello.conf"));
        company = system.actorOf(Props.create(null), "company");
        employee = system.actorOf(Props.create(null), "employee");
        Timeout timeout = new Timeout(1, TimeUnit.SECONDS);
        for (int i = 1; i < 20; i++) {
            company.tell(new Coordinated(i, timeout), ActorRef.noSender());
            Thread.sleep(200);
            Integer companyCount = null;//(Integer) Await.result(ask(company, "GetCount", timeout));
            Integer employeeCount = null;//(Integer) Await.result(ask(employee, "GetCount", timeout));
            System.out.println("companyCount = " + companyCount);
            System.out.println("employeeCount = " + employeeCount);
            System.out.println("==========================");
        }
    }
}
