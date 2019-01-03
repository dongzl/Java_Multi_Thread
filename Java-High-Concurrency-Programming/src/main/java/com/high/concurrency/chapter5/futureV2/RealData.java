package com.high.concurrency.chapter5.futureV2;

import java.util.concurrent.Callable;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/03 上午9:24
 */
public class RealData implements Callable<String> {

    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
