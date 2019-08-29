package me.maxwell.common.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Maxwell.Lee
 * @version 3.8.1
 * @company Scho Techonlogy Co. Ltd
 * @date 2019/8/29 20:27
 */
public class JsonHelper {

    public static Float getFloat(JSONObject obj, String name1, String name2) {
        if (obj.containsKey(name1)) {
            return obj.getFloat(name1);
        } else {
            return obj.getFloat(name2);
        }
    }

    public static Long getLong(JSONObject obj, String name1, String name2) {
        if (obj.containsKey(name1)) {
            return obj.getLong(name1);
        } else {
            return obj.getLong(name2);
        }
    }

    public static Integer getInteger(JSONObject obj, String name1, String name2) {
        if (obj.containsKey(name1)) {
            return obj.getInteger(name1);
        } else {
            return obj.getInteger(name2);
        }
    }

    public static String getString(JSONObject obj, String name1, String name2) {
        if (obj.containsKey(name1)) {
            return obj.getString(name1);
        } else {
            return obj.getString(name2);
        }
    }

    public static Object getObject(JSONObject obj, String name1, String name2) {
        if (obj.containsKey(name1)) {
            return obj.get(name1);
        } else {
            return obj.get(name2);
        }
    }

    public static JSONObject getJSONObject(JSONObject obj, String name1, String name2) {
        if (obj.containsKey(name1)) {
            return obj.getJSONObject(name1);
        } else {
            return obj.getJSONObject(name2);
        }
    }

    public static JSONArray getJSONArray(JSONObject obj, String name1, String name2) {
        if (obj.containsKey(name1)) {
            return obj.getJSONArray(name1);
        } else {
            return obj.getJSONArray(name2);
        }
    }

    public static Float getFloatLikeName(JSONObject obj, String name) {
        return getFloat(obj, name, name.replace('-', ' '));
    }

    public static Long getLongLikeName(JSONObject obj, String name) {
        return getLong(obj, name, name.replace('-', ' '));
    }

    public static Integer getIntegerLikeName(JSONObject obj, String name) {
        return getInteger(obj, name, name.replace('-', ' '));
    }

    public static String getStringLikeName(JSONObject obj, String name) {
        return getString(obj, name, name.replace('-', ' '));
    }

    public static Object getObjectLikeName(JSONObject obj, String name) {
        return getObject(obj, name, name.replace('-', ' '));
    }

    public static JSONObject getJSONObjectLikeName(JSONObject obj, String name) {
        return getJSONObject(obj, name, name.replace('-', ' '));
    }

    public static JSONArray getJSONArrayLikeName(JSONObject obj, String name) {
        return getJSONArray(obj, name, name.replace('-', ' '));
    }
}
