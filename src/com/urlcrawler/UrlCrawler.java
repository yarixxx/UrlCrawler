package com.urlcrawler;

import java.io.IOException;
import java.net.URL;
import java.util.Queue;

public class UrlCrawler {

    private static Integer urlLimit;
    private static Integer counter;
    private static URL startUrl;
    private static Queue<URL> todoUrls;
    private static UrlFetcher urlFetcher;
    private static LineParserImpl lineParser;

    public static void main(String[] args) throws Exception {
        todoUrls.add(startUrl);
        lineParser = new LineParserImpl();

        initFetcher();
        startLoop();
    }

    private static void initFetcher() {
        urlFetcher = new UrlFetcher();
        urlFetcher.setCounter(counter);
        urlFetcher.setLimit(urlLimit);
        urlFetcher.setTodoUrls(todoUrls);
        urlFetcher.setLineParser(lineParser);
    }

    private static void startLoop() throws IOException {
        while (!urlFetcher.isFinished()) {
            urlFetcher.fetchUrl();
        }
    }
}
