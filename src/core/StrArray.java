package core;

import com.sun.jna.*;
import gtnative.*;

public class StrArray {
  protected Pointer str_array;

  public StrArray() {
    this.str_array = GT.INSTANCE.gt_str_array_new();
  }

  public StrArray(Pointer str_array) {
    this.str_array = str_array;
  }

  public String get(int index) {
    NativeLong itmp = new NativeLong(index);
    return GT.INSTANCE.gt_str_array_get(this.str_array, itmp);
  }

  protected void finalize() {
    GT.INSTANCE.gt_str_array_delete(this.str_array);
  }

  public void add_array(String[] array) {
    for (String cstr : array) {
      GT.INSTANCE.gt_str_array_add_cstr(this.str_array, cstr);
    }
  }

  public String[] to_a() {
    String[] arr = new String[this.length()];
    for (int i = 0; i < this.length(); i++) {
      NativeLong tmp = new NativeLong(i);
      arr[i] = GT.INSTANCE.gt_str_array_get(this.str_array, tmp);
    }
    return arr;
  }

  public int length() {
    NativeLong ntmp = GT.INSTANCE.gt_str_array_size(this.str_array);
    return (int) ntmp.longValue();
  }

  public Pointer to_ptr() {
    return this.str_array;
  }

}
