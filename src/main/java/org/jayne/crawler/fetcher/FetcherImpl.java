package org.jayne.crawler.fetcher;

import org.apache.commons.httpclient.methods.GetMethod;
import org.jayne.crawler.data.FetchResult;
import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;
import org.jayne.crawler.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
public class FetcherImpl implements Fetcher {

    private static final Map<UrlType, ProfileFetcher> PROFILE_FETCHER_MAP = new HashMap<UrlType, ProfileFetcher>();

    private static final Map<UrlType, ContentFetcher> CONTENT_FETCHER_MAP = new HashMap<UrlType, ContentFetcher>();

    public static void registerProfileFetcher(UrlType urlType, ProfileFetcher profileFetcher) {
        PROFILE_FETCHER_MAP.put(urlType, profileFetcher);
    }

    public static void registerContentFetcher(UrlType urlType, ContentFetcher contentFetcher) {
        CONTENT_FETCHER_MAP.put(urlType, contentFetcher);
    }

    @Autowired
    private DoNothingProfileFetcher doNothingProfileFetcher;

    @Autowired
    private DoNothingContentFetcher doNothingContentFetcher;

    @Override
    public ExecResult<FetchResult> fetch(UrlType urlType, String url) {
        ProfileFetcher profileFetcher = PROFILE_FETCHER_MAP.get(urlType);
        if (null == profileFetcher) {
            profileFetcher = doNothingProfileFetcher;
        }
        ContentFetcher contentFetcher = CONTENT_FETCHER_MAP.get(urlType);
        if (null == contentFetcher) {
            contentFetcher = doNothingContentFetcher;
        }
        String urlValue;
        try {
            urlValue = getUrlValueAsStream(url);
        } catch (Exception e) {
            return ExecResult.fail("get url value error");
        }
        ExecResult<String> profileValue = ExecResult.fail("get profile fail");
        try {
            profileValue = profileFetcher.fetch(url, urlValue);
        } catch (Exception e) {
        }
        if (!profileValue.isSuccess()) {
            return ExecResult.fail("get profile fail");
        }
        ExecResult<String> contentValue = ExecResult.fail("get content fail");
        try {
            contentValue = contentFetcher.fetch(url, urlValue);
        } catch (Exception e) {
        }
        if (!contentValue.isSuccess()) {
            return ExecResult.fail("get content fail");
        }
        return ExecResult.success(new FetchResult(url, urlValue, profileValue.getData(), contentValue.getData()));
    }

    private String getUrlValueAsStream(String url) throws IOException {
        GetMethod httpMethod = new GetMethod(url);
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            int statusCode = HttpClientUtils.getClient().executeMethod(httpMethod);
            if (200 != statusCode) {
                throw new IOException("HttpUtils: status code error! [" + statusCode + "] " + httpMethod.getURI().toString());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpMethod.getResponseBodyAsStream()));
            String str = "";
            while ((str = reader.readLine()) != null) {
                stringBuffer.append(str);
            }
        } finally {
            httpMethod.releaseConnection();
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {

    }
}
