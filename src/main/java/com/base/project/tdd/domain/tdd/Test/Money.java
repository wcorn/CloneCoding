package com.base.project.tdd.domain.tdd.Test;

public class Money implements Expression {

    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    public boolean equals(Object object) {
        if (getClass() != object.getClass())
            return false;
        Money money = (Money) object;
        return amount == money.amount && currency().equals(money.currency);
    }

    String Currency() {
        return currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public String currency() {
        return currency;
    }

    public Expression plus(Expression addend) {
        return new Sum(this,addend);
    }

    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency,to);
        return new Money(amount/rate,to);
    }

    @Override
    public String toString() {
        return amount+" "+currency;
    }
}