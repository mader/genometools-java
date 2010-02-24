/*
  Copyright (c) 2009      Philipp Carpus  <random234@gmx.net>
  Copyright (c)      2010 Sascha Steinbiss <steinbiss@zbh.uni-hamburg.de>
  Copyright (c) 2009-2010 Center for Bioinformatics, University of Hamburg

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

import gtnative.*;
import extended.GenomeNode;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import core.GTerrorJava;
import core.GTerror;

public abstract class GenomeStream {
  protected Pointer genome_stream = null;
  private boolean disposed;

  public void set_disposed(boolean bool) {
    disposed = bool;
  }
  
  public GenomeNode next_tree() throws GTerrorJava {
    GTerror err = new GTerror();
    PointerByReference genome_node = new PointerByReference();
    int rval;
    rval = GT.INSTANCE.gt_node_stream_next(this.genome_stream, genome_node, err
        .to_ptr());
    if (rval != 0) {
      throw new GTerrorJava(err.get_err());
    }
    if (genome_node.getValue() == Pointer.NULL) {
      return null;
    } else {
      return new GenomeNode(genome_node.getValue());
    }
  }

  public synchronized void dispose() {
    if (!disposed) {
      GT.INSTANCE.gt_node_stream_delete(genome_stream);
      disposed = true;
    }
  }
 
  protected  void finalize() {
    if (!disposed) {
      dispose();
    }
  }
  
  public Pointer to_ptr() {
    return genome_stream;
  }
}
