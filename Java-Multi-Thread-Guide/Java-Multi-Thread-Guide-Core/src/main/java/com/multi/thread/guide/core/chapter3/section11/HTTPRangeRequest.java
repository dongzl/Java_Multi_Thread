package com.multi.thread.guide.core.chapter3.section11;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/16 上午9:48
 */
public class HTTPRangeRequest {

    private final Range range;

    private String url;

    public HTTPRangeRequest(String url, int lowerBound, int upperBound) {
        this.range = new Range(lowerBound, upperBound);
        this.url = url;
    }

    public static class Range {
        private long lowerBound;
        private long upperBound;

        public Range(long lowerBound, long upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public long getLowerBound() {
            return lowerBound;
        }

        public void setLowerBound(long lowerBound) {
            this.lowerBound = lowerBound;
        }

        public long getUpperBound() {
            return upperBound;
        }

        public void setUpperBound(long upperBound) {
            this.upperBound = upperBound;
        }
    }
}
