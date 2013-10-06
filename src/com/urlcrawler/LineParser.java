package com.urlcrawler;

import java.net.URL;
import java.util.List;

public interface LineParser {
    public List<URL> extractUrl(String line);
}
