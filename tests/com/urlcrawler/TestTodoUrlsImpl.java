package com.urlcrawler;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestTodoUrlsImpl {

    private TodoUrls todoUrls;
    private URL firstLink;
    private URL secondLink;
    private URL actualLink;

    @Before
    public void setUp() throws Exception {
        todoUrls = new TodoUrlsImpl();
        firstLink = new URL("http://firstlink.com");
        secondLink = new URL("http://secondlink.com");
    }

    @Test
    public void testAddedUrlBecomeAvailableAsNextUrl() {
        givenFirstLink();
        whenNextUrlCalled();
        thenActualUrlEqualsToFirstUrl();
    }

    @Test
    public void testAddedUrlsListBecomeAvailableAsNextUrls() {
        givenUrlsList();
        whenNextUrlCalled();
        thenActualUrlEqualsToFirstUrl();
    }

    private void givenFirstLink() {
        todoUrls.addUrl(firstLink);
    }

    private void givenUrlsList() {
        List<URL> links = new LinkedList<URL>();
        links.add(firstLink);
        links.add(secondLink);
        todoUrls.addUrls(links);
    }

    private void whenNextUrlCalled() {
        actualLink = todoUrls.nextUrl();
    }

    private void thenActualUrlEqualsToFirstUrl() {
        assertEquals(firstLink, actualLink);
    }
}
