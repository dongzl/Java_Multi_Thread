package com.high.concurrency.chapter5.future;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/02 下午8:16
 */
public class Client {

    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }
}
