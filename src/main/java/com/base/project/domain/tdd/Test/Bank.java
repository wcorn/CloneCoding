package com.base.project.domain.tdd.Test;

import java.util.Hashtable;

public class Bank {
    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    private final Hashtable<Pair, Integer> rates = new Hashtable<>();

    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) return 1;
        return rates.get(new Pair(from, to));
    }
}
