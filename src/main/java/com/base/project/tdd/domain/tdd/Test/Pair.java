package com.base.project.tdd.domain.tdd.Test;

public class Pair {
    private String from;
    private String to;
    public Pair(String from, String to){
        this.from= from;
        this.to = to;
    }
    public boolean equals(Object object){
        if (getClass() != object.getClass())
            return false;
        Pair pair = (Pair) object;
        return from .equals(pair.from)&&to.equals(pair.to);
    }
    public int hashCode(){
        return 0;
    }
}
