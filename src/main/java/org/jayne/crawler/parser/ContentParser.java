package org.jayne.crawler.parser;

import org.jayne.crawler.data.Content;
import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;

import java.util.List;

/**
 * 帖子信息解析
 */
public abstract class ContentParser {

    public ContentParser() {
        ParserImpl.registerContentParser(getUrlType(), this);
    }

    protected abstract UrlType getUrlType();

    public abstract ExecResult<List<Content>> parse(String url, String urlValue, String contentValue);
}
