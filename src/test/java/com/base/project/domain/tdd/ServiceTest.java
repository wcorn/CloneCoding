package com.base.project.domain.tdd;

import com.base.project.tdd.domain.tdd.Test.Bank;
import com.base.project.tdd.domain.tdd.Test.Expression;
import com.base.project.tdd.domain.tdd.Test.Money;
import com.base.project.tdd.domain.tdd.Test.Sum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ServiceTest {
    @Test
    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    public void testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

    @Test
    public void testFrancMultiplication() {
        Money five = Money.franc(5);
        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    public void testSimpleAddition() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));

        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1),"USD");
        assertEquals(Money.dollar(1),result);
    }

    @Test
    public void restReduceMoneyDifferentCurrency(){
        Bank bank = new Bank();
        bank.addRate("CHF","USD",2);
        Money result = bank.reduce(Money.franc(2),"USD");
        assertEquals(Money.dollar(1),result);
    }

    @Test
    public void testIdentityRate(){
        assertEquals(1, new Bank().rate("USD","USD"));
    }

    @Test
    public void testMixedAddition(){
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF","USD",2);
        Money result = bank.reduce(
                fiveBucks.plus(tenFrancs),"USD");
        assertEquals(Money.dollar(10),result);
    }
    @Test
    public void testSumPlusMoney(){
        Expression fiveBucks= Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF","USD",2);
        Expression sum = new Sum(fiveBucks,tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum,"USD");
        assertEquals(Money.dollar(15),result);
    }
    @Test
    public void testSumTimes(){
        Expression fiveBucks= Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF","USD",2);
        Expression sum = new Sum(fiveBucks,tenFrancs).times(2);
        Money result = bank.reduce(sum,"USD");
        assertEquals(Money.dollar(20),result);
    }
}