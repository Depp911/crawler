package org.jayne.crawler.normalizer;

import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
public class NormalizerImpl implements Normalizer {

    private static final Map<UrlType, BaseNormalizer> NORMALIZER_MAP = new HashMap<UrlType, BaseNormalizer>();

    public static void registerNormalizer(UrlType urlType, BaseNormalizer normalizer) {
        NORMALIZER_MAP.put(urlType, normalizer);
    }

    @Autowired
    private DoNothingNormalizer doNothingNormalizer;

    @Override
    public ExecResult<String> normalize(UrlType urlType, String url) {
        BaseNormalizer normalizer = NORMALIZER_MAP.get(urlType);
        if (null == normalizer) {
            normalizer = doNothingNormalizer;
        }
        try {
            return normalizer.normalize(url);
        } catch (Exception e) {
            return ExecResult.fail("normalize fail");
        }
    }
}
