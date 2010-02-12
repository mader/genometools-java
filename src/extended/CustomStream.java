/*
  Copyright (c) 2010 Sascha Steinbiss <steinbiss@zbh.uni-hamburg.de>
  Copyright (c) 2010 Center for Bioinformatics, University of Hamburg

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


package extended;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import core.GTerror;
import gtnative.GT;
import gtnative.GT.STREAMNEXTFUNC;

public abstract class CustomStream extends GenomeStream {
  private STREAMNEXTFUNC next_func;
 
  protected abstract GenomeNode next() throws Exception;

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
