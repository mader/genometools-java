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


package annotationsketch;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

import core.GTerror;
import core.GTerrorJava;
import gtnative.GT;

public class Layout {
  protected Pointer layout_ptr;

  public Layout(Diagram diagram, int width, Style style) throws GTerrorJava {
    GTerror err = new GTerror();
    NativeLong w_long = new NativeLong(width);
    synchronized (this) {
      layout_ptr = GT.INSTANCE.gt_layout_new(diagram.to_ptr(), w_long, style
          .to_ptr(), err.to_ptr());
    }
    if (layout_ptr == null) {
      throw new GTerrorJava(err.get_err());
    }

  }

  protected synchronized void finalize() throws Throwable {
    try {
      GT.INSTANCE.gt_layout_delete(layout_ptr);
    } finally {
      super.finalize();
    }
  }

  public long get_height() {
    return GT.INSTANCE.gt_layout_get_height(layout_ptr).longValue();
  }

  public synchronized void sketch(Canvas canvas) throws GTerrorJava {
    GTerror err = new GTerror();
    int had_err = GT.INSTANCE.gt_layout_sketch(layout_ptr, canvas.to_ptr(), err
        .to_ptr());
    if (had_err < 0) {
      throw new GTerrorJava(err.get_err());
    }
  }

  public Pointer to_ptr() {
    return layout_ptr;
  }
}
