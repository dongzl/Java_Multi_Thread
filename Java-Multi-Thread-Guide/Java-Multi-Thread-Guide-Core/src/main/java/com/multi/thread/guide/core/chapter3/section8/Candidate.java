package com.multi.thread.guide.core.chapter3.section8;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午9:38
 */
public class Candidate implements Iterable<Endpoint> {

    private final Set<Endpoint> endpoints;

    public final int totalWeight;

    public Candidate(Set<Endpoint> endpoints) {
        int sum = 0;
        for (Endpoint endpoint : endpoints) {
            sum += endpoint.getWeight();
        }
        totalWeight = sum;
        this.endpoints = endpoints;
    }

    @Override
    public void forEach(Consumer<? super Endpoint> action) {

    }

    @Override
    public Spliterator<Endpoint> spliterator() {
        return null;
    }

    @Override
    public Iterator<Endpoint> iterator() {
        return null;
    }
}
