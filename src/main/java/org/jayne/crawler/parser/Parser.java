package org.jayne.crawler.parser;

import org.jayne.crawler.data.FetchResult;
import org.jayne.crawler.data.ParseResult;
import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;

/**
 * 根据抓取的结果解析用户信息和帖子信息
 */
public interface Parser {

    ExecResult<ParseResult> parse(UrlType urlType, FetchResult fetchResult);
}
