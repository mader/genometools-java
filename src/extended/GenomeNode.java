/*
  Copyright (c) 2009      Philipp Carpus  <random234@gmx.net>
  Copyright (c) 2009-2010 Sascha Steinbiss <steinbiss@zbh.uni-hamburg.de>
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

import com.sun.jna.*;
import gtnative.GT;

import core.GTerror;
import core.GTerrorJava;
import core.Range;
import core.Str;

public class GenomeNode {
  protected TransparentPointer genome_node_ptr;
  private boolean disposed;

  public GenomeNode() {
  }

  protected void set_disposed(boolean bool) {
    disposed = bool;
  }
  
  public GenomeNode(Pointer node_ptr) {
    synchronized (this) {
      genome_node_ptr = new TransparentPointer(GT.INSTANCE
          .gt_genome_node_ref(node_ptr));
    }
    disposed = false;
  }

  public synchronized void dispose() {
    if (!disposed) {
      GT.INSTANCE.gt_genome_node_delete(this.genome_node_ptr);
      disposed = true;
    }
  }
  
  protected void finalize() {
    
  }

  public Range get_range() {
    NativeLong nstart = GT.INSTANCE
        .gt_genome_node_get_start(this.genome_node_ptr);
    NativeLong nend = GT.INSTANCE.gt_genome_node_get_end(this.genome_node_ptr);
    Range ran = new Range((int) nstart.longValue(), (int) nend.longValue());
    return ran;
  }

  public String get_filename() {
    return GT.INSTANCE.gt_genome_node_get_filename(this.genome_node_ptr);
  }

  public void accept(NodeVisitor visitor) throws GTerrorJava {
    GTerror err = new GTerror();
    int rval = GT.INSTANCE.gt_genome_node_accept(this.genome_node_ptr, visitor
        .to_ptr(), err.to_ptr());
    if (rval != 0) {
      throw new GTerrorJava(err.get_err());
    }
  }

  public int hashCode() {
    return this.genome_node_ptr.hashCode();
  }

  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if ((obj == null))
      return false;
    GenomeNode gn = (GenomeNode) obj;
    return (this.to_ptr().equals(gn.to_ptr()));
  }

  public void change_seqid(String seqid) {
    Str id = new Str(seqid);
    GT.INSTANCE.gt_genome_node_change_seqid(genome_node_ptr, id.to_ptr());
  }

  public Pointer to_ptr() {
    return genome_node_ptr;
  }

  public long get_address() {
    return genome_node_ptr.getPeer();
  }

  public String get_seqid() {
    Str s = new Str(GT.INSTANCE.gt_genome_node_get_seqid(genome_node_ptr));
    return s.to_s();
  }
}
