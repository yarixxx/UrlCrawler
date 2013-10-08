package com.urlcrawler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

public class UrlCrawler {
    private final static String CONFIG_FILE = "config.properties";
    private static Integer urlLimit;
    private static Integer counter = 1;
    private static URL startUrl;
    private static Queue<URL> todoUrls = new LinkedList<URL>();
    private static UrlFetcher urlFetcher;
    private static LineParserImpl lineParser;
    private static String urlRegEx;

    public static void main(String[] args) throws Exception {
        initConfiguration();
        todoUrls.add(startUrl);
        lineParser = new LineParserImpl();
        lineParser.setRegularExpression(urlRegEx);

        initFetcher();
        startLoop();
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
