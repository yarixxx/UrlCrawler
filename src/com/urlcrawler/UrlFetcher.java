package com.urlcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Queue;

public class UrlFetcher {
    private URL url;
    private BufferedReader in;
    private String inputLine;
    private Queue<URL> todoUrls;
    private Integer counter;
    private Integer limit;
    private LineParserImpl lineParser;

    public void setLineParser(LineParserImpl lineParser) {
        this.lineParser = lineParser;
    }

    public boolean isFinished() {
        return todoUrls.isEmpty() || counter > limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public void setTodoUrls(Queue<URL> todoUrls) {
        this.todoUrls = todoUrls;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void fetchUrl() throws IOException {
        if (!isFinished()) {
            fetchNextUrl();
        }
    }

    private void fetchNextUrl() throws IOException {
        setUrl(todoUrls.remove());
        initBufferedReader();
        while (nextLine()) {
            List<URL> links = lineParser.extractUrl(inputLine);
            if (!links.isEmpty()) {
                todoUrls.addAll(links);
                counter += links.size();
            }
        }
        in.close();
    }

    private void initBufferedReader() throws IOException {
        in = new BufferedReader(new InputStreamReader(getUrl().openStream()));
    }

    private boolean nextLine() throws IOException {
        inputLine = in.readLine();
        return (inputLine == null);
    }

}
