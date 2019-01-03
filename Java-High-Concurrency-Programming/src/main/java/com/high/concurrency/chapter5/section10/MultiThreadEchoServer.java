package com.high.concurrency.chapter5.section10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/03 下午7:02
 */
public class MultiThreadEchoServer {

    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class HandleMsg implements Runnable {

        Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);
                String inputLine = null;
                long b = System.currentTimeMillis();
                while ((inputLine = reader.readLine()) != null) {
                    writer.println(inputLine);
                }
                long e = System.currentTimeMillis();
                System.out.println("speed:" + (e - b) + "ms");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                    clientSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket echoSocket = null;
        Socket clientSocket = null;
        try {
            echoSocket = new ServerSocket(8000);
        } catch (Exception e) {
            System.out.println(e);
        }
        while (true) {
            try {
                clientSocket = echoSocket.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + "connect!");
                exec.execute(new HandleMsg(clientSocket));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
