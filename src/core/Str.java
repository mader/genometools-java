package core;

import com.sun.jna.*;
import gtnative.*;

public class Str {
  private Pointer str_ptr;

  public Str(String cstr) {
    if (cstr.equals(null)) {
      str_ptr = GT.INSTANCE.gt_str_new();
    } else {
      str_ptr = GT.INSTANCE.gt_str_new_cstr(cstr);
    }
  }

  public Str(Pointer str) {
    str_ptr = GT.INSTANCE.gt_str_ref(str);
  }

  protected void finalize() {
    GT.INSTANCE.gt_str_delete(str_ptr);
  }

  public void append_str(Str str) {
    GT.INSTANCE.gt_str_append_str(str_ptr, str.to_ptr());
  }

  public void append_str(String str) {
    GT.INSTANCE.gt_str_append_cstr(str_ptr, str);
  }

  public String to_s() {
    return GT.INSTANCE.gt_str_get(str_ptr);
  }

  public long length() {
    return GT.INSTANCE.gt_str_length(str_ptr).longValue();
  }

  public Pointer to_ptr() {
    return str_ptr;
  }

}
