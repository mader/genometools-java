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

import extended.FeatureNode;

public class Array {
  private Pointer array_ptr;

  // Size of Element is meant to be the size of a Pointer on the Target system
  public Array(int size_of_elem) {
    NativeLong tmp = new NativeLong(size_of_elem);
    array_ptr = GT.INSTANCE.gt_array_new(tmp);
    array_ptr = GT.INSTANCE.gt_array_ref(this.array_ptr);
  }

  public Array(Pointer elem) {
    array_ptr = elem;
  }

  protected void finalize() {
    GT.INSTANCE.gt_array_delete(this.array_ptr);
  }

  public Pointer get(int index) {
    NativeLong tmp = new NativeLong(index);
    return GT.INSTANCE.gt_array_get(this.array_ptr, tmp).getPointer(0);
  }

  public void add(FeatureNode elem) {
    GT.INSTANCE.gt_array_add_ptr(this.array_ptr, elem.to_ptr());
  }

  public void add(Pointer elem) {
    GT.INSTANCE.gt_array_add_ptr(this.array_ptr, elem);
  }

  public int size() {
    NativeLong ntmp = GT.INSTANCE.gt_array_size(this.array_ptr);
    return (int) ntmp.longValue();
  }

  public Pointer to_ptr() {
    return this.array_ptr;
  }
}
