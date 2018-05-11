package org.jayne.crawler.storage;

import org.jayne.crawler.data.FetchResult;
import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecInfo;

/**
 *
 */
public interface Storage {

    ExecInfo store(UrlType urlType, FetchResult data);
}
