package com.urlcrawler;

public class UrlCounterImpl implements UrlCounter {

    private Integer urlCounter;

    @Override
    public Integer getValue() {
        return urlCounter;
    }

    @Override
    public void setValue(Integer number) {
        urlCounter = number;
    }

    @Override
    public void addValue(Integer number) {
        urlCounter += number;
    }

}
