package org.jayne.crawler.utils;

import java.util.Date;

/**
 * Created by jayne on 2018/5/10.
 */
public interface ExecResponse {
    Date getCurrentTime();

    int getCode();

    String getApiErrorMessage();
}
