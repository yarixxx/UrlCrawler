package com.urlcrawler;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TodoUrlsImpl implements TodoUrls {
    Set<UrlItem> links;

    TodoUrlsImpl() {
        links = new LinkedHashSet<UrlItem>();
    }

    @Override
    public void addUrl(UrlItem link) {
        links.add(link);

    }

    @Override
    public void addUrls(List<UrlItem> links) {
        for (UrlItem link : links) {
            addUrl(link);
        }
    }

    @Override
    public UrlItem nextUrl() {
        Iterator<UrlItem> i = links.iterator();
        return i.next();
    }

    @Override
    public Boolean isEmpty() {
        return links.isEmpty();
    }

}
