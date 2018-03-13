package ru.javawebinar.topjava.util;

import java.util.Map;

public class CollectionUtil {
    public static String mapToString(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        map.forEach((k, v) -> sb.append(k).append(':').append(v).append(';'));
        sb.append('}');
        return sb.toString();
    }
}
