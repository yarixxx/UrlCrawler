package com.urlcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class BufferReaderFactory {
    public static BufferedReader getNewBufferReader(URL url) throws IOException {
        return new BufferedReader(getNewInputStreamReader(url));
    }

    public static InputStreamReader getNewInputStreamReader(URL url)
            throws IOException {
        return new InputStreamReader(url.openStream());
    }
}
