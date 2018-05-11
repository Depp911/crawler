package org.jayne.crawler;

import org.jayne.crawler.data.FetchResult;
import org.jayne.crawler.data.ParseResult;
import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.fetcher.Fetcher;
import org.jayne.crawler.normalizer.Normalizer;
import org.jayne.crawler.parser.Parser;
import org.jayne.crawler.storage.Storage;
import org.jayne.crawler.utils.ExecInfo;
import org.jayne.crawler.utils.ExecResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jayne on 2018/5/9.
 */
@Service
public class Crawler {

    @Autowired
    private Normalizer normalizer;

    @Autowired
    private Fetcher fetcher;

    @Autowired
    private Storage storage;

    @Autowired
    private Parser parser;

    public ExecInfo syncContent(String url) {
        UrlType urlType = UrlType.judge(url);
        if (null == urlType) {
            return ExecInfo.fail("unrecongnized url");
        }
        ExecResult<ParseResult> parseExecResult = parseUrl(urlType, url);
        if (!parseExecResult.isSuccess()) {
            return ExecInfo.fail("parse error");
        }
        return ExecInfo.success();
    }

    private ExecResult<ParseResult> parseUrl(UrlType urlType, String url) {
        ExecResult<String> normalizeExecResult = normalizer.normalize(urlType, url);
        if (!normalizeExecResult.isSuccess()) {
            return ExecResult.fail("normalize error");
        }
        url = normalizeExecResult.getData();
        ExecResult<FetchResult> fetchExecResult = fetcher.fetch(urlType, url);
        if (!fetchExecResult.isSuccess()) {
            return ExecResult.fail("fetch error");
        }
        storage.store(urlType, fetchExecResult.getData());
        return parser.parse(urlType, fetchExecResult.getData());
    }

    public static void main(String[] args) {

    }
}
