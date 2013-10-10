package com.urlcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class UrlFetcher {
    private URL url;
    private BufferedReader in;
    private String inputLine;
    private TodoUrls todoUrls;
    private UrlCounter counter;
    private Integer limit;
    private LineParserImpl lineParser;

    public void setLineParser(LineParserImpl lineParser) {
        this.lineParser = lineParser;
    }

    public boolean isFinished() {
        return todoUrls.isEmpty() || counter.getValue() > limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setCounter(UrlCounter counter) {
        this.counter = counter;
    }

    public void setTodoUrls(TodoUrls todoUrls) {
        this.todoUrls = todoUrls;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void fetchUrl() throws IOException {
        setUrl(todoUrls.nextUrl());
        System.out.println("fetchUrl: " + getUrl());
        initBufferedReader();
        while (nextLine()) {
            List<URL> links = lineParser.extractUrl(inputLine);
            if (!links.isEmpty()) {
                todoUrls.addUrls(links);
                counter.addValue(links.size());
            }
        }
        if (in != null) {
            in.close();
        }
    }

    private void initBufferedReader() throws IOException {
        try {
            in = new BufferedReader(
                    new InputStreamReader(getUrl().openStream()));
        } catch (IOException e) {
            in = null;
            System.out.println("Failed to load url: " + getUrl());
        }
    }

    private boolean nextLine() throws IOException {
        if (in == null) {
            return false;
        }
        inputLine = in.readLine();
        return !(inputLine == null);
    }

}
