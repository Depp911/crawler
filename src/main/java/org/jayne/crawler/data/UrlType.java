package org.jayne.crawler.data;

/**
 * url类型，根据不同的url类型使用不同的抓取和解析方法
 */
public enum UrlType {

    /** www.instagram.com/xxx */
    INSTAGRAM {
        @Override
        public boolean match(String url) {
            return url.matches("http(|s)://(|www\\.)instagram\\.com/.*");
        }
    },

    DO_NOTHING {
        @Override
        public boolean match(String url) {
            return false;
        }
    };

    public abstract boolean match(String url);

    public static UrlType judge(String url) {
        for (UrlType urlType : values()) {
            if (urlType.match(url)) {
                return urlType;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(INSTAGRAM.match("http://instagram.com/callmegray#"));
        System.out.println(INSTAGRAM.match("https://www.instagram.com/caramel_milk73/"));
    }
}
