package core;
import com.sun.jna.Library;
import com.sun.jna.Native;
import gtnative.*;

public abstract class Allocators {

  private static boolean hasrun = false;

  public static void init() {
    if (!hasrun) {
      GT.INSTANCE.gt_allocators_init();
      GT.INSTANCE.gt_allocators_reg_atexit_func();
      hasrun = true;
    }
  }
}
