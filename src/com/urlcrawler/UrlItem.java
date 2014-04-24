package com.urlcrawler;

import java.net.URL;

public class UrlItem {

    UrlItem(URL link, Status status) {
        this.link = link;
        this.status = status;
    }

    public String toString() {
        return link.toString();
    }

    private URL link;
    private Status status;

    public URL getLink() {
        return link;
    }

    public void setLink(URL links) {
        this.link = links;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        WAITING, FAILED, FINISHED, WORKING
    }
}
