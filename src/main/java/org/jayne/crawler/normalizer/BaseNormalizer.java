package org.jayne.crawler.normalizer;

import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;

/**
 *
 */
public abstract class BaseNormalizer {

    public BaseNormalizer() {
        NormalizerImpl.registerNormalizer(getUrlType(), this);
    }

    protected abstract UrlType getUrlType();

    public abstract ExecResult<String> normalize(String url);
}
