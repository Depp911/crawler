package org.jayne.crawler.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.Map;

/**
 * Created by jayne on 2018/5/9.
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = getMapper();

    public JsonUtils() {
    }

    private static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static <T> T fromJson(String json, Class<T> klass) {
        try {
            return MAPPER.readValue(json, klass);
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> klass, Map<Class<?>, JsonDeserializer<?>> deserializers) {
        ObjectMapper mapper = getMapper();
        Module module = new SimpleModule("", Version.unknownVersion(), deserializers);
        mapper.registerModules(new Module[]{module});

        try {
            return mapper.readValue(json, klass);
        } catch (IOException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static <T> String toJson(T value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static <T> String toPrettyJson(T value) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            return null;
        }
    }
}
