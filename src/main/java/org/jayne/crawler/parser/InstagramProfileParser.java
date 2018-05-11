package org.jayne.crawler.parser;

import org.apache.commons.lang.StringUtils;
import org.jayne.crawler.data.Profile;
import org.jayne.crawler.data.UrlType;
import org.jayne.crawler.utils.ExecResult;
import org.jayne.crawler.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
@Service
public class InstagramProfileParser extends ProfileParser {

    private static final Pattern NAME_PATTERN = Pattern.compile("http(|s)://(|www\\.)instagram\\.com/(.*?)($|/.*)");

    @Override
    protected UrlType getUrlType() {
        return UrlType.INSTAGRAM;
    }

    @Override
    public ExecResult<Profile> parse(String url, String urlValue, String profileValue) {
        Map<String, Object> profileData;
        try {
            profileData = getProfileMap(profileValue);
        } catch (Exception e) {
            return ExecResult.fail("数据解析失败");
        }
        String id = getId(url);
        if (StringUtils.isBlank(id)) {
            return ExecResult.fail("ID解析出错");
        }
        Object name = profileData.get("username");
        if (null == name) {
            return ExecResult.fail("名字解析出现");
        }
        Object avatar = profileData.get("profile_pic_url");
        if (null == avatar) {
            return ExecResult.fail("头像解析出错");
        }
        Profile profile = new Profile();
        profile.setId(id);
        profile.setName(name.toString());
        profile.setAvatar(avatar.toString());
        return ExecResult.success(profile);
    }

    private Map<String, Object> getProfileMap(String profileValue) {
        Map<String, Object> data = JsonUtils.fromJson(profileValue, Map.class);
        data = (Map<String, Object>) data.get("entry_data");
        data = ((List<Map<String, Object>>)data.get("ProfilePage")).get(0);
        data = (Map<String, Object>)data.get("graphql");
        data = (Map<String, Object>) data.get("user");
        return data;
    }

    private String getId(String url) {
        Matcher matcher = NAME_PATTERN.matcher(url);
        if (!matcher.find()) {
            return null;
        }
        return matcher.group(3);
    }

    public static void main(String[] args) {
        Matcher matcher = NAME_PATTERN.matcher("https://www.instagram.com/swe_bunnies/");
        System.out.println(matcher.find());
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));
        System.out.println(matcher.group(4));
//        System.out.println(matcher.group(5));
    }
}
