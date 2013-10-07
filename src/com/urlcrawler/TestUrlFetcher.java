package com.urlcrawler;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class TestUrlFetcher {
    private UrlFetcher urlFetcher;
    private boolean isFinished;

    @Before
    public void setUp() throws Exception {
        urlFetcher = new UrlFetcher();
    }

    @Test
    public void testUrlFetcherWasFinishedWhenQueueIsEmpty() {
        givenTodoUrlsListIsEmpty();
        whenIsFinishedCalled();
        thenUrlFetcherWasFinished();
    }

    @Test
    public void testUrlFetcherWasFinishedWhenCounterIsGreaterThenLimit()
            throws MalformedURLException {
        givenTodoUrlsListIsNotEmpty();
        givenCounterIsTenAndLimitIsFive();
        whenIsFinishedCalled();
        thenUrlFetcherWasFinished();
    }

    private void givenCounterIsTenAndLimitIsFive() {
        urlFetcher.setCounter(10);
        urlFetcher.setLimit(5);
    }

    private void givenTodoUrlsListIsNotEmpty() throws MalformedURLException {
        Queue<URL> links = new LinkedList<URL>();
        links.add(new URL("http://y3x.ru/"));
        urlFetcher.setTodoUrls(links);
    }

    private void thenUrlFetcherWasFinished() {
        assertTrue(isFinished);
    }

    private void givenTodoUrlsListIsEmpty() {
        urlFetcher.setTodoUrls(new LinkedList<URL>());
    }

    private void whenIsFinishedCalled() {
        isFinished = urlFetcher.isFinished();
    }

}
