package com.urlcrawler;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestTodoUrlsImpl {

    private TodoUrls todoUrls;
    private UrlItem firstLink;
    private UrlItem secondLink;
    private UrlItem actualLink;

    @Before
    public void setUp() throws Exception {
        todoUrls = new TodoUrlsImpl();
        firstLink = new UrlItem(new URL("http://firstlink.com"), UrlItem.Status.WAITING);
        secondLink = new UrlItem(new URL("http://secondlink.com"), UrlItem.Status.WAITING);
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
        List<UrlItem> links = new LinkedList<UrlItem>();
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
