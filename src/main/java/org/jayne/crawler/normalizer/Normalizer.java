package org.jayne.crawler.normalizer;

import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;

/**
 * 处理原始的url
 */
public interface Normalizer {

    /**
     * 处理特殊Url
     * @param urlType
     * @param url
     * @return
     */
    ExecResult<String> normalize(UrlType urlType, String url);
}
