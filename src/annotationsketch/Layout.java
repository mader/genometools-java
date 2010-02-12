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
