package org.jayne.crawler.utils;

/**
 * Created by jayne on 2018/5/10.
 */
public class PageInfo {
    private int page = 1;
    private int count;

    public PageInfo(int page, int count) {
        if(page < 1) {
            page = 1;
        }

        this.page = page;
        this.count = count;
    }

    public int getPage() {
        return this.page;
    }

    public int getCount() {
        return this.count;
    }

    public int from() {
        return (this.page - 1) * this.count;
    }

    public int to() {
        return this.page * this.count;
    }
}
