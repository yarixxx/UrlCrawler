package com.urlcrawler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class UrlCrawler {
    private final static String CONFIG_FILE = "config.properties";
    private static Integer urlLimit;
    private static UrlCounter counter;
    private static URL startUrl;
    private static TodoUrls todoUrls;
    private static UrlFetcher urlFetcher;
    private static LineParserImpl lineParser;
    private static String urlRegEx;

    public static void main(String[] args) throws Exception {
        initConfiguration();
        initLineParser();
        initUrlCounter();
        initTodoUrls();

        todoUrls.addUrl(startUrl);

        initFetcher();
        startLoop();
    }

    private static void initTodoUrls() {
        todoUrls = new TodoUrlsImpl();
    }

    private static void initUrlCounter() {
        counter = new UrlCounterImpl();
        counter.setValue(1);
    }

    private static void initLineParser() {
        lineParser = new LineParserImpl();
        lineParser.setRegularExpression(urlRegEx);
    }

    private static void initConfiguration() throws FileNotFoundException,
            IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(CONFIG_FILE));
        startUrl = new URL(properties.getProperty("startUrl"));
        urlLimit = Integer.parseInt(properties.getProperty("urlLimit"));
        urlRegEx = properties.getProperty("urlRegEx");
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
