package org.jayne.crawler.fetcher;

import org.jayne.crawler.data.FetchResult;
import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;

/**
 * 用户和帖子信息抓取
 */
public interface Fetcher {

    ExecResult<FetchResult> fetch(UrlType urlType, String url);
}
