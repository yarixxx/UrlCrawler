package com.urlcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

public class UrlFetcher {
    private UrlItem url;
    private BufferedReader bufferedReader;
    private String inputLine;
    private TodoUrls todoUrls;
    private UrlCounter counter;
    private Integer limit;
    private LineParser lineParser;

    public void setLineParser(LineParser lineParser) {
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

    public void setUrl(UrlItem url) {
        this.url = url;
        this.url.setStatus(UrlItem.Status.WORKING);
    }

    public UrlItem getUrl() {
        return url;
    }

    public void fetchUrl() {
        UrlItem urlItem = todoUrls.nextUrl();
        if (!urlItem.getStatus().equals(UrlItem.Status.WAITING)) {
            return;
        }
        setUrl(urlItem);
        System.out.println("fetchUrl: " + getUrl());
        initBufferedReader();
        while (nextLine()) {
            List<UrlItem> links = lineParser.extractUrl(inputLine);
            if (!links.isEmpty()) {
                todoUrls.addUrls(links);
                counter.addValue(links.size());
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        url.setStatus(UrlItem.Status.FINISHED);
    }

    private void initBufferedReader() {
        try {
            bufferedReader = BufferReaderFactory.getNewBufferReader(getUrl());
        } catch (ConnectException e) {
            url.setStatus(UrlItem.Status.FAILED);
            System.out.println("Connection failed: " + getUrl());
        } catch (IOException e) {
            bufferedReader = null;
            url.setStatus(UrlItem.Status.FAILED);
            System.out.println("Failed to load url: " + getUrl());
            e.printStackTrace();
        }
    }

    private boolean nextLine() {
        if (bufferedReader == null) {
            return false;
        }
        try {
            inputLine = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Error while reading url: " + e.getMessage());
        }
        return !(inputLine == null);
    }

}
