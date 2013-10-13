package com.urlcrawler;

import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TodoUrlsImpl implements TodoUrls {

    Set<URL> links;

    TodoUrlsImpl() {
        links = new LinkedHashSet<URL>();
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
        Iterator<URL> i = links.iterator();
        return i.next();
    }

    @Override
    public Boolean isEmpty() {
        return links.isEmpty();
    }

}
