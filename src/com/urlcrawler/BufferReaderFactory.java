package com.urlcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferReaderFactory {
    public static BufferedReader getNewBufferReader(UrlItem url)
            throws IOException {
        return new BufferedReader(getNewInputStreamReader(url));
    }

    public static InputStreamReader getNewInputStreamReader(UrlItem url)
            throws IOException {
        return new InputStreamReader(url.getLink().openStream());
    }
}
