/*
  Copyright (c) 2009 Philipp Carpus  <random234@gmx.net>
  Copyright (c) 2009 Center for Bioinformatics, University of Hamburg

  Permission to use, copy, modify, and distribute this software for any
  purpose with or without fee is hereby granted, provided that the above
  copyright notice and this permission notice appear in all copies.

  THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
  WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
  MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
  ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
  WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
  ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
  OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
*/


package core;

import com.sun.jna.*;
import gtnative.*;

public class StrArray {
  protected Pointer str_array;
  private boolean disposed;

  public StrArray() {
    this.str_array = GT.INSTANCE.gt_str_array_new();
    disposed = false;
  }

  public StrArray(Pointer str_array) {
    this.str_array = str_array;
    disposed = false;
  }
  
  public StrArray(String[] stra) {
    this.str_array = GT.INSTANCE.gt_str_array_new();
    this.add_array(stra);
    disposed = false;
  }

  public String get(int index) {
    NativeLong itmp = new NativeLong(index);
    return GT.INSTANCE.gt_str_array_get(this.str_array, itmp);
  }

  public synchronized void dispose() {
    if (!disposed) {
      GT.INSTANCE.gt_str_array_delete(this.str_array);
      disposed = true;
    }
  }
  
  protected void finalize() {
    if (!disposed) {
      dispose();
    }
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
