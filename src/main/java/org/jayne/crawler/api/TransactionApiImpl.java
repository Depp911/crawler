package org.jayne.crawler.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用于在非接口实现方法内使用事务而设计的Api
 */
@Service
public class TransactionApiImpl implements TransactionApi {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public <T> T runInTransaction(Executable<T> executable) {
        return executable.exec();
    }

}
