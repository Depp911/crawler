package org.jayne.crawler.fetcher;

import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
@Service
public class InstagramContentFetcher extends ContentFetcher {

    private static final Pattern DATA_PATTERN = Pattern.compile("<script type=\"text/javascript\">window\\._sharedData = (.*?);</script>");

    @Override
    protected UrlType getUrlType() {
        return UrlType.INSTAGRAM;
    }

    @Override
    public ExecResult<String> fetch(String url, String urlValue) {
        Matcher matcher = DATA_PATTERN.matcher(urlValue);
        if (!matcher.find()) {
            return ExecResult.fail("InstagramContentFetcher:fetch not find");
        }
        return ExecResult.success(matcher.group(1));
    }
}
