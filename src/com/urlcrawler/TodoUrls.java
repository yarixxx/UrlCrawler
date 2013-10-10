package com.urlcrawler;

import java.net.URL;
import java.util.List;

public interface TodoUrls {
    public void addUrl(URL link);

    public void addUrls(List<URL> links);

    public URL nextUrl();

    public Boolean isEmpty();
}
