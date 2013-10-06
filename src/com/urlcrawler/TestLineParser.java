package com.urlcrawler;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestLineParser {

    private LineParser lineParser;
    private final static String LINE_WITH_URL = "some stuff http://y3x.ru/ some more";
    private static final String REGULAR_EXPRESSION = "";
    List<URL> links;

    @Before
    public void setUp() throws Exception {
        lineParser = new LineParserImpl();
    }

    @Test
    public void testExtractUrl() throws MalformedURLException {
        givenRegularExpression();
        whenExtractUrlCalled();
        thenOneUrlExtracted();
    }

    private void givenRegularExpression() {
        lineParser.setRegularExpression(REGULAR_EXPRESSION);
    }

    private void whenExtractUrlCalled() {
        links = lineParser.extractUrl(LINE_WITH_URL);
    }

    private void thenOneUrlExtracted() throws MalformedURLException {
        assertEquals(links.size(), 1);
        assertEquals(links.get(0), new URL("http://y3x.ru/"));

    }

}
