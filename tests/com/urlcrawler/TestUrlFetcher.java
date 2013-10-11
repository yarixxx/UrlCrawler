package com.urlcrawler;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class TestUrlFetcher {
    private UrlFetcher urlFetcher;
    private boolean isFinished;

    private TodoUrls todoUrls;
    private LineParser lineParser;
    private UrlCounter counter;
    private BufferReaderFactory bufferReaderFactory;

    private URL url;

    // TodoUrls

    @Before
    public void setUp() throws Exception {
        urlFetcher = new UrlFetcher();
        todoUrls = mock(TodoUrls.class);
        lineParser = mock(LineParser.class);
        counter = mock(UrlCounter.class);
        bufferReaderFactory = mock(BufferReaderFactory.class);
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

    @Test
    public void testFetchUrl() throws IOException {
        givenTodoUrlsWithOneUrl();
        givenLineParser();
        whenFetchUrlCalled();
        verify(todoUrls).nextUrl();
        // verify(BufferReaderFactory.class).getNewBufferReader(url);
    }

    private void givenLineParser() {
        urlFetcher.setLineParser(lineParser);
    }

    private void givenCounterIsTenAndLimitIsFive() {
        when(counter.getValue()).thenReturn(10);
        urlFetcher.setCounter(counter);
        urlFetcher.setLimit(5);
    }

    private void givenTodoUrlsWithOneUrl() throws MalformedURLException {
        url = new URL("http://y3x.ru/");
        when(todoUrls.nextUrl()).thenReturn(url);
        urlFetcher.setTodoUrls(todoUrls);
    }

    private void givenTodoUrlsListIsNotEmpty() throws MalformedURLException {
        when(todoUrls.isEmpty()).thenReturn(false);
        urlFetcher.setTodoUrls(todoUrls);
    }

    private void givenTodoUrlsListIsEmpty() {
        when(todoUrls.isEmpty()).thenReturn(true);
        urlFetcher.setTodoUrls(todoUrls);
    }

    private void whenIsFinishedCalled() {
        isFinished = urlFetcher.isFinished();
    }

    private void whenFetchUrlCalled() {
        urlFetcher.fetchUrl();
    }

    private void thenUrlFetcherWasFinished() {
        assertTrue(isFinished);
    }

}
