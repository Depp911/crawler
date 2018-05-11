package org.jayne.crawler.storage;

import org.jayne.crawler.data.FetchResult;
import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecInfo;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class StorageImpl implements Storage {

    @Override
    public ExecInfo store(UrlType urlType, FetchResult data) {
        return ExecInfo.success();
    }
}
