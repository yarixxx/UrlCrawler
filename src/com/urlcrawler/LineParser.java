package com.urlcrawler;

import java.util.List;

public interface LineParser {
    public List<UrlItem> extractUrl(String line);

    public void setRegularExpression(String regex);
}
