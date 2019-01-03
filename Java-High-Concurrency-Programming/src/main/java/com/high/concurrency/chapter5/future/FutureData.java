package com.high.concurrency.chapter5.future;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/02 下午8:11
 */
public class FutureData implements Data {

    protected RealData realData = null;

    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
