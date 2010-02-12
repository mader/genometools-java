package extended;

import gtnative.*;
import extended.GenomeNode;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import core.GTerrorJava;
import core.GTerror;

public abstract class GenomeStream {
  Pointer genome_stream = null;

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

  public Pointer to_ptr() {
    return genome_stream;
  }
}
