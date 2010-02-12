package extended;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import core.GTerror;
import gtnative.GT;
import gtnative.GT.STREAMNEXTFUNC;

public abstract class CustomStream extends GenomeStream {
  private STREAMNEXTFUNC next_func;
 
  protected abstract GenomeNode next();
  
  public CustomStream() {
    final CustomStream cs = this;
    next_func = new STREAMNEXTFUNC() {
      public int callback(PointerByReference nodepp, Pointer err_p) {
        GTerror err = new GTerror(err_p);
        try {
          GenomeNode gn = cs.next();
          if (gn != null) {
            nodepp.setValue(gn.to_ptr());
          } else {
            nodepp.setValue(Pointer.NULL);
          }
        } catch (Exception e) {
          err.set(e.toString());
          return -1;
        }
        return 0;
      }
    };
    genome_stream = GT.INSTANCE.gt_script_wrapper_stream_new(next_func, Pointer.NULL);
  }
}
