package extended;

import com.sun.jna.*;
import gtnative.GT;

import core.GTerror;
import core.GTerrorJava;
import core.Range;
import core.Str;

class GenomeNode {
  protected TransparentPointer genome_node_ptr;

  public GenomeNode() {
  }

  public GenomeNode(Pointer node_ptr) {
    synchronized (this) {
      genome_node_ptr = new TransparentPointer(GT.INSTANCE
          .gt_genome_node_ref(node_ptr));
    }
  }

  protected synchronized void finalize() {
    GT.INSTANCE.gt_genome_node_delete(this.genome_node_ptr);
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
    int rval = GT.INSTANCE.gt_genome_node_accept(this.genome_node_ptr,
                                                 visitor.to_ptr(),
                                                 err.to_ptr());
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
