package org.jayne.crawler.normalizer;

import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DoNothingNormalizer extends BaseNormalizer {

    @Override
    protected UrlType getUrlType() {
        return UrlType.DO_NOTHING;
    }

    @Override
    public ExecResult<String> normalize(String url) {
        return ExecResult.success(url);
    }
}
