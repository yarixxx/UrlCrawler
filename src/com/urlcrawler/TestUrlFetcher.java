package com.urlcrawler;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;

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
        UrlCounter counter = mock(UrlCounter.class);
        when(counter.getValue()).thenReturn(10);
        urlFetcher.setCounter(counter);
        urlFetcher.setLimit(5);
    }

    private void givenTodoUrlsListIsNotEmpty() throws MalformedURLException {
        TodoUrls todoUrls = mock(TodoUrls.class);
        when(todoUrls.isEmpty()).thenReturn(false);
        urlFetcher.setTodoUrls(todoUrls);
    }

    private void thenUrlFetcherWasFinished() {
        assertTrue(isFinished);
    }

    private void givenTodoUrlsListIsEmpty() {
        TodoUrls todoUrls = mock(TodoUrls.class);
        when(todoUrls.isEmpty()).thenReturn(true);
        urlFetcher.setTodoUrls(todoUrls);
    }

    private void whenIsFinishedCalled() {
        isFinished = urlFetcher.isFinished();
    }

}
