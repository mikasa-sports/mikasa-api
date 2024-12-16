package com.mikasa.util;

import com.mikasa.exception.BaseException;
import java.util.function.Supplier;

public class BooleanUtils {

  private BooleanUtils() {}

  public static void throwIfTrue(boolean b, BaseException e) {
    if (b) {
      throw e;
    }
  }

  public static void runIfTrue(boolean b, Runnable r) {
    if (b) {
      r.run();
    }
  }


  public static void throwIfFalse(boolean b, BaseException e) {
    throwIfTrue(!b, e);
  }

  public static void throwIfTrue(boolean b, Supplier<BaseException> e) {
    if (b) {
      throw e.get();
    }
  }

  public static void throwIfFalse(boolean b, Supplier<BaseException> e) {
    throwIfTrue(!b, e);
  }
}
