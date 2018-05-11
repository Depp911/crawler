package org.jayne.crawler.fetcher;

import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;

/**
 * 用户信息抓取
 */
public abstract class ProfileFetcher {

    public ProfileFetcher() {
        FetcherImpl.registerProfileFetcher(getUrlType(), this);
    }

    protected abstract UrlType getUrlType();

    public abstract ExecResult<String> fetch(String url, String urlValue);
}
