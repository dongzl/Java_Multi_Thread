package com.multi.thread.guide.core.chapter6.section3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/23 下午7:39
 */
public class BigImmutableObject implements Iterable<Map.Entry<String, BigImmutableObject.BigObject>> {

    private final HashMap<String, BigObject> registry;

    public BigImmutableObject(HashMap<String, BigObject> registry) {
        this.registry = registry;
    }

    public BigImmutableObject(BigImmutableObject prototype, String key, BigObject newValue) {
        this(createRegistry(prototype, key, newValue));
    }

    private static HashMap<String,BigObject> createRegistry(BigImmutableObject prototype, String key, BigObject newValue) {
        HashMap<String, BigObject> newRegistry = (HashMap<String, BigObject>)prototype.registry.clone();
        newRegistry.put(key, newValue);
        return newRegistry;
    }

    @Override
    public Iterator<Map.Entry<String, BigObject>> iterator() {
        final Set<Map.Entry<String, BigObject>> readOnlyEntries = Collections.unmodifiableSet(registry.entrySet());
        //return ReadOnlyIterator.with(readOnlyEntries.iterator());
        return null;
    }

    public BigObject getObject(String key) {
        return registry.get(key);
    }

    public BigImmutableObject update(String key, BigObject newValue) {
        return new BigImmutableObject(this, key, newValue);
    }

    class BigObject {

    }
}
