package com.snail.base.serviceloader;

import java.util.ServiceLoader;

/**
 * 封装自己的ServiceLoader，可根据范型直接返回指定的类型
 */
public final class EasyServiceLoader {
    private EasyServiceLoader() {
    }

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}
