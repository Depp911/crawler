package org.jayne.crawler.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一个线程安全的DateFormat实现
 * <p />
 * {@link SimpleDateFormat}的创建是比较耗时的，每次都new一个使用会消耗一定的系统性能
 * （一般情况下我们并不关心这点性能，但是在不改变代码结构的情况下，通过简单的修改就可以实现高性能的方案却是我们应该追求的），
 * 所以应该放置一个全局的一直使用，但{@link SimpleDateFormat}不是线程安全的，因此需要实现一个线程安全的DateFormat。
 * <br />
 * 该类通过{@link ThreadLocal}封装{@link SimpleDateFormat}实现了一个线程安全的DateFormat
 *
 */
public class ThreadLocalDateFormat {

    private final ThreadLocal<SimpleDateFormat> THREAD_LOCAL;

    public ThreadLocalDateFormat(final String format) {
        THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat(format);
            }
        };
    }

    public String format(Date date) {
        return THREAD_LOCAL.get().format(date);
    }

    public Date parse(String date) throws ParseException {
        return THREAD_LOCAL.get().parse(date);
    }
}
