package org.jayne.crawler.parser;

import org.jayne.crawler.data.*;
import org.jayne.crawler.utils.ExecResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author KONG Xiangxin
 */
@Service
public class ParserImpl implements Parser {

    private static final Map<UrlType, ProfileParser> PROFILE_PARSER_MAP = new HashMap<UrlType, ProfileParser>();

    private static final Map<UrlType, ContentParser> CONTENT_PARSER_MAP = new HashMap<UrlType, ContentParser>();

    public static void registerProfileParser(UrlType urlType, ProfileParser profileParser) {
        PROFILE_PARSER_MAP.put(urlType, profileParser);
    }

    public static void registerContentParser(UrlType urlType, ContentParser contentParser) {
        CONTENT_PARSER_MAP.put(urlType, contentParser);
    }

    @Override
    public ExecResult<ParseResult> parse(UrlType urlType, FetchResult fetchResult) {
        String url = fetchResult.getUrl();
        ProfileParser profileParser = PROFILE_PARSER_MAP.get(urlType);
        if (null == profileParser) {
            return ExecResult.fail("no profile parser found");
        }
        ContentParser contentParser = CONTENT_PARSER_MAP.get(urlType);
        if (null == contentParser) {
            return ExecResult.fail("no content parser found");
        }
        String urlValue = fetchResult.getUrlValue();
        String profileValue = fetchResult.getProfileValue();
        ExecResult<Profile> profile = ExecResult.fail("parse profile error");
        try {
            profile = profileParser.parse(url, urlValue, profileValue);
        } catch (Exception e) {
        }
        if (!profile.isSuccess()) {
            return ExecResult.fail("parse profile error");
        }
        String contentValue = fetchResult.getContentValue();
        ExecResult<List<Content>> contents = ExecResult.fail("parse content error");
        try {
            contents = contentParser.parse(url, urlValue, contentValue);
        } catch (Exception e) {
        }
        if (!contents.isSuccess()) {
            return ExecResult.fail("parse content error");
        }
        return ExecResult.success(new ParseResult(urlType, url, profile.getData(), contents.getData()));
    }
}
