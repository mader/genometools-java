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
import core.Str;
import gtnative.GT;
import gtnative.GT.TRACKORDERINGFUNC;

public class Layout {
  protected Pointer layout_ptr;
  private boolean disposed;
  @SuppressWarnings("unused")
  private TRACKORDERINGFUNC tof;
  @SuppressWarnings("unused")
  private OrderingFunction of;

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
    disposed = false;
  }
  
  public void set_track_ordering_func(final OrderingFunction of) {
	    TRACKORDERINGFUNC tof = new TRACKORDERINGFUNC() {
		@Override
		public NativeLong callback(Pointer str1_ptr, Pointer str2_ptr,
				Pointer data_ptr) {
			Str str1 = new Str(str1_ptr);
			Str str2 = new Str(str2_ptr);
			int i = 0;
			try {
		      i = of.orderFunction(str1, str2);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    NativeLong order_long = new NativeLong(i);
		    return order_long;
		}
	    };
	    this.tof = tof;
	    this.of = of;
	    GT.INSTANCE.gt_layout_set_track_ordering_func(layout_ptr, tof);
  }
  
  public void unset_track_ordering_func() {
	    GT.INSTANCE.gt_layout_unset_track_ordering_func(layout_ptr);
  }
  
  public synchronized void dispose() {
    if (!disposed) {
      GT.INSTANCE.gt_layout_delete(layout_ptr);
      disposed = true;
    }
  }
  
  protected void finalize() throws Throwable {
    try {
      if (!disposed) {
        dispose();
      }
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
