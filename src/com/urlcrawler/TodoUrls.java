package com.urlcrawler;

import java.util.List;

public interface TodoUrls {
    public void addUrl(UrlItem link);

    public void addUrls(List<UrlItem> links);

    public UrlItem nextUrl();

    public Boolean isEmpty();
}
