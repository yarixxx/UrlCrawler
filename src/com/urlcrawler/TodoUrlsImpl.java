package com.urlcrawler;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TodoUrlsImpl implements TodoUrls {

    Queue<URL> links;

    TodoUrlsImpl() {
        links = new LinkedList<URL>();
    }

    @Override
    public void addUrl(URL link) {
        links.add(link);

    }

    @Override
    public void addUrls(List<URL> links) {
        for (URL link : links) {
            addUrl(link);
        }
    }

    @Override
    public URL nextUrl() {
        return links.poll();
    }

    @Override
    public Boolean isEmpty() {
        return links.isEmpty();
    }

}
