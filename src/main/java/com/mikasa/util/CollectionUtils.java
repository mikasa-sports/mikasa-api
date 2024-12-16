package com.mikasa.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtils {

  private CollectionUtils() {
  }

  public static <T> List<T> emptyIfNull(List<T> list) {
    if (list == null) {
      return List.of();
    }
    return list;
  }

  public static <T> Set<T> emptyIfNull(Set<T> set) {
    if (set == null) {
      return Set.of();
    }
    return set;
  }

  public static <T,R> Map<T,R> emptyIfNull(Map<T,R> map) {
    if (map == null) {
      return Map.of();
    }
    return map;
  }
}
