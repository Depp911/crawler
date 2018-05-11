package org.jayne.crawler.parser;

import org.jayne.crawler.data.Profile;
import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;

/**
 * 用户信息解析
 */
public abstract class ProfileParser {

    public ProfileParser() {
        ParserImpl.registerProfileParser(getUrlType(), this);
    }

    protected abstract UrlType getUrlType();

    public abstract ExecResult<Profile> parse(String url, String urlValue, String profileValue);
}
