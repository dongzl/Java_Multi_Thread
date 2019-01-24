package com.multi.thread.guide.core.chapter6.section5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/24 下午3:25
 */
public class SyncCollectionSafeTraversal {

    final List<String> syncList = Collections.synchronizedList(new ArrayList<>());

    public void dump() {
        Iterator<String> iterator = syncList.iterator();
        synchronized (syncList) {
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
}
