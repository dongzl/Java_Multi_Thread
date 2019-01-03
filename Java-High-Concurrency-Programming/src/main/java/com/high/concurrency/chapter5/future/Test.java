package com.high.concurrency.chapter5.future;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/02 下午8:18
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("数据 = " + data.getResult());
    }
}
