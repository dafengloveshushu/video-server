package com.jushu.video.api;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * @author 大奉
 * @create 2019-11-19 15:30
 */
public class ObjectUtil extends ObjectUtils {

    public ObjectUtil() {
    }

    public static boolean isNotEmpty(@Nullable Object obj) {
        return !isEmpty(obj);
    }

}
