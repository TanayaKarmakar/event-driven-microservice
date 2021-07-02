package com.app.t2k.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author t0k02w6 on 02/07/21
 * @project event-driven-microservice
 */
public class CollectionsUtil {
    private CollectionsUtil() {

    }

    private static class CollectionsUtilHolder {
        static final CollectionsUtil INSTANCE = new CollectionsUtil();
    }

    public static CollectionsUtil getInstance() {
        return CollectionsUtilHolder.INSTANCE;
    }

    public <T> List<T> getListFromIterable(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
}
