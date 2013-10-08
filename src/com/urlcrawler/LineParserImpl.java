package com.urlcrawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineParserImpl implements LineParser {

    private Pattern regPattern;
    private List<URL> links = new ArrayList<URL>();

    public List<URL> extractUrl(String line) {
        Matcher m = regPattern.matcher(line);
        while (m.find()) {
            try {
                System.out.println("Converting string to URL: " + m.group(1));
                URL url = new URL(m.group(1));
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
