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

package core;

import gtnative.GT;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

public class Bioseq {
  private Pointer bioseq_ptr;
  private boolean disposed;

  public Bioseq(String filename) throws GTerrorJava {
    if (filename == null) {
      throw new NullPointerException("got null filename");
    }
    GTerror err = new GTerror();
    bioseq_ptr = GT.INSTANCE.gt_bioseq_new(filename, err.to_ptr());
    if (bioseq_ptr == Pointer.NULL) {
      throw new GTerrorJava(err.get_err());
    }
    disposed = false;
  }

  public synchronized void dispose() {
    if (!disposed) {
    	GT.INSTANCE.gt_bioseq_delete(bioseq_ptr);
      disposed = true;
    }
  }
  
  protected void finalize() {
    if (!disposed) {
      dispose();
    }
  }
  
  public long get_number_of_sequences() {
    NativeLong l = GT.INSTANCE.gt_bioseq_number_of_sequences(this.bioseq_ptr);
    return l.longValue();
  }

  public long get_sequence_length(long index) {
    NativeLong length = GT.INSTANCE.gt_bioseq_get_sequence_length(bioseq_ptr,
        new NativeLong(index));
    return length.longValue();
  }

  public String get_description(long index) {
    return GT.INSTANCE.gt_bioseq_get_description(bioseq_ptr, new NativeLong(
        index));
  }

  public Pointer to_ptr() {
    return this.bioseq_ptr;
  }
}
