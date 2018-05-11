package org.jayne.crawler.fetcher;

import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DoNothingProfileFetcher extends ProfileFetcher {

    @Override
    protected UrlType getUrlType() {
        return UrlType.DO_NOTHING;
    }

    @Override
    public ExecResult<String> fetch(String url, String urlValue) {
        return ExecResult.success("");
    }
}
