package com.multi.thread.guide.core.chapter6.section4;

import java.security.SecureRandom;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/24 上午9:57
 */
public enum ThreadSpecificSecureRandom {

    INSTANCE;

    final static ThreadLocal<SecureRandom> SECURE_RANDOM = new ThreadLocal<SecureRandom>() {
        @Override
        protected SecureRandom initialValue() {
            SecureRandom srnd;
            try {
                srnd = SecureRandom.getInstance("SHA1PRNG");
            } catch (Exception e) {
                srnd = new SecureRandom();
                e.printStackTrace();
            }
            srnd.nextBytes(new byte[20]);
            return srnd;
        }
    };

    public int nextInt(int upperBound) {
        SecureRandom secureRnd = SECURE_RANDOM.get();
        return secureRnd.nextInt(upperBound);
    }

    public void setSeed(long seed) {
        SecureRandom secureRnd = SECURE_RANDOM.get();
        secureRnd.setSeed(seed);
    }
}
