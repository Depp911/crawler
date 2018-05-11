package org.jayne.crawler.tool.common;

import java.io.PrintWriter;

/**
 * 本地工具
 */
public abstract class AbstractLocalTool {

    protected PrintWriter out;

    protected PrintWriter err;

    public void setPrintWriter(PrintWriter out, PrintWriter err) {
        this.out = out;
        this.err = err;
    }

    /**
     * 使用方法
     *
     * @return
     */
    public abstract void usage(PrintWriter out);

    /**
     * 功能说明
     *
     * @return
     */
    public abstract String comment();

    /**
     * 执行方法
     *
     * @param args 参数
     */
    public abstract void exec(String[] args) throws Exception;
}
