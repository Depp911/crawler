package org.jayne.crawler.fetcher;

import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;

/**
 * 帖子信息抓取
 */
public abstract class ContentFetcher {

    public ContentFetcher() {
        FetcherImpl.registerContentFetcher(getUrlType(), this);
    }

    protected abstract UrlType getUrlType();

    public abstract ExecResult<String> fetch(String url, String urlValue);
}
