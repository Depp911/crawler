package org.jayne.crawler.data;

/**
 *
 */
public class FetchResult {

    private String url;

    private String urlValue;

    private String profileValue;

    private String contentValue;

    public FetchResult(String url, String urlValue, String profileValue, String contentValue) {
        this.url = url;
        this.urlValue = urlValue;
        this.profileValue = profileValue;
        this.contentValue = contentValue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlValue() {
        return urlValue;
    }

    public void setUrlValue(String urlValue) {
        this.urlValue = urlValue;
    }

    public String getProfileValue() {
        return profileValue;
    }

    public void setProfileValue(String profileValue) {
        this.profileValue = profileValue;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }
}
