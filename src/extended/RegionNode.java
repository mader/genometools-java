package extended;

import gtnative.GT;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.TransparentPointer;

import core.GTerrorJava;
import core.Str;

public class RegionNode extends GenomeNode {
  public RegionNode(Pointer node_ptr) {
    super(node_ptr);
  }

  public RegionNode(RegionNode node) {
    super(node.to_ptr());
  }

  public RegionNode(String seqid, long start, long end)
      throws NullPointerException, GTerrorJava {
    if (seqid == null) {
      throw new NullPointerException("trying to pass null as seqid");
    }
    if (start > end) {
      throw new GTerrorJava("start position (" + start
          + ") is after end position (" + end + ")");
    }
    Str s = new Str(seqid);
    NativeLong nl_start = new NativeLong(start), nl_end = new NativeLong(end);
    Pointer newfn = GT.INSTANCE
        .gt_region_node_new(s.to_ptr(), nl_start, nl_end);
    this.genome_node_ptr = new TransparentPointer(newfn);
  }
}
