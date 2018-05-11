package org.jayne.crawler.dao;

import org.nutz.dao.ConnCallback;
import org.nutz.dao.impl.DaoRunner;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by jayne on 2018/5/9.
 */
@Repository
public class SpringDaoRunner implements DaoRunner {

    public SpringDaoRunner(){}

    @Override
    public void run(DataSource dataSource, ConnCallback connCallback) {
        Connection con = DataSourceUtils.getConnection(dataSource);

        try {
            connCallback.invoke(con);
        } catch (Exception e) {
            if(e instanceof RuntimeException) {
                throw (RuntimeException)e;
            }

            throw new RuntimeException(e);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }
}
