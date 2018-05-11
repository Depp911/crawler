package org.jayne.crawler.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 帖子信息
 */
public class Content {

    private String id = "";

    private String title = "";

    private List<String> images = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void addImage(String image) {
        images.add(image);
    }
}
