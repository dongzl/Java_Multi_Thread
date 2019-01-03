package com.high.concurrency.chapter5.future;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/02 下午8:11
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String para) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
