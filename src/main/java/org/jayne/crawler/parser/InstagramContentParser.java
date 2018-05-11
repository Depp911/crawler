package org.jayne.crawler.parser;

import org.jayne.crawler.data.Content;
import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;
import org.jayne.crawler.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class InstagramContentParser extends ContentParser {

    @Override
    protected UrlType getUrlType() {
        return UrlType.INSTAGRAM;
    }

    @Override
    public ExecResult<List<Content>> parse(String url, String urlValue, String contentValue) {
        List<Map<String, Object>> contentsData;
        try {
            contentsData = getContentMap(contentValue);
        } catch (Exception e) {
            return ExecResult.fail("数据解析失败");
        }
        List<Content> contents = new ArrayList<Content>();
        for (Map<String, Object> map : contentsData) {
            Content content;
            try {
                content = parseContent(map);
            } catch (Exception e) {
                continue;
            }
            contents.add(content);
        }
        return ExecResult.success(contents);
    }

    private List<Map<String, Object>> getContentMap(String profileValue) {
        Map<String, Object> data = JsonUtils.fromJson(profileValue, Map.class);
        data = (Map<String, Object>) data.get("entry_data");
        data = ((List<Map<String, Object>>)data.get("ProfilePage")).get(0);
        data = (Map<String, Object>)data.get("graphql");
        data = (Map<String, Object>) data.get("user");
        data = (Map<String, Object>) data.get("edge_owner_to_timeline_media");
        return (List<Map<String, Object>>) data.get("edges");
    }

    private Content parseContent(Map<String, Object> map) {
        Content content = new Content();
        Map<String, Object> data = (Map<String, Object>)map.get("node");
        content.setId(data.get("id").toString());
        content.addImage(data.get("thumbnail_src").toString());
        if (data.containsKey("edge_media_to_caption")) {
            data = (Map<String, Object>)data.get("edge_media_to_caption");
            data = ((List<Map<String, Object>>)data.get("edges")).get(0);
            data = (Map<String, Object>)data.get("node");
            content.setTitle(data.get("text").toString());
        }
        return content;
    }
}
