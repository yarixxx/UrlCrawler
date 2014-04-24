package com.urlcrawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineParserImpl implements LineParser {

    private Pattern regPattern;
    private List<UrlItem> links = new ArrayList<UrlItem>();

    public List<UrlItem> extractUrl(String line) {
        links.clear();
        Matcher m = regPattern.matcher(line);
        while (m.find()) {
            try {
                System.out.println("Converting string to URL: " + m.group(1));
                UrlItem url = new UrlItem(new URL(m.group(1)), UrlItem.Status.WAITING);
                links.add(url);
            } catch (MalformedURLException e) {
            }
        }
        return links;
    }

    public void setRegularExpression(String regex) {
        this.regPattern = Pattern.compile(regex);
    }

}
