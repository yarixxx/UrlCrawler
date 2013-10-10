package com.urlcrawler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestUrlCounterImpl {
    private UrlCounter urlCounter;
    private Integer actualValue;
    private final static Integer EXPECTED_VALUE = 10;
    private final static Integer EXPECTED_VALUE_TWO_TIMES_GREATER = EXPECTED_VALUE
            + EXPECTED_VALUE;

    @Before
    public void setUp() throws Exception {
        urlCounter = new UrlCounterImpl();
    }

    @Test
    public void testThatSettedValueAvailable() {
        givenValueSetted();
        whenGetValueCalled();
        thenActualValueEqualsToExpectedValue();
    }

    @Test
    public void testThatAddValueIncrementsActualValue() {
        givenValueSetted();
        givenValueAdded();
        whenGetValueCalled();
        thenActualValueTwoTimesGreater();
    }

    private void givenValueSetted() {
        urlCounter.setValue(EXPECTED_VALUE);
    }

    private void givenValueAdded() {
        urlCounter.addValue(EXPECTED_VALUE);
    }

    private void whenGetValueCalled() {
        actualValue = urlCounter.getValue();
    }

    private void thenActualValueEqualsToExpectedValue() {
        assertEquals(EXPECTED_VALUE, actualValue);
    }

    private void thenActualValueTwoTimesGreater() {
        assertEquals(EXPECTED_VALUE_TWO_TIMES_GREATER, actualValue);
    }

}
