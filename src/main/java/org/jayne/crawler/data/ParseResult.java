package org.jayne.crawler.data;

import java.util.Collections;
import java.util.List;

/**
 *
 */
public class ParseResult {

    private UrlType urlType;

    private String url;

    private Profile profile;

    private List<Content> contents = Collections.emptyList();

    public ParseResult(UrlType urlType, String url, Profile profile, List<Content> contents) {
        this.urlType = urlType;
        this.url = url;
        this.profile = profile;
        this.contents = contents;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UrlType getUrlType() {
        return urlType;
    }

    public void setUrlType(UrlType urlType) {
        this.urlType = urlType;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
