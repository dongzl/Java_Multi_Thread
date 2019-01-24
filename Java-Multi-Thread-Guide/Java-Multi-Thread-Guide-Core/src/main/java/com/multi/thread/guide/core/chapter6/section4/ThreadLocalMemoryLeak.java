package com.multi.thread.guide.core.chapter6.section4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/24 下午2:48
 */
public class ThreadLocalMemoryLeak extends HttpServlet {

    final static ThreadLocal<Counter> counterHolder = new ThreadLocal<Counter>() {
        @Override
        protected Counter initialValue() {
            Counter tosCounter = new Counter();
            return tosCounter;
        }
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
        PrintWriter pwr = resp.getWriter();
        pwr.printf("Thread %s, counter: %d", Thread.currentThread().getName(), counterHolder.get().getAndIncreament());
    }

    void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counterHolder.get().getAndIncreament();
    }

    static class Counter {
        private int i = 0;
        public int getAndIncreament() {
            return i++;
        }
    }
}
