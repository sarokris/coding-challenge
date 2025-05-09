package com.ahold.technl.sandbox.util;

import java.util.Collections;
import java.util.List;

public class CollectionUtil {
    public static <T> List<T> emptyIfNull(List<T> list){
        return list == null ? Collections.emptyList() : list;
    }
}
