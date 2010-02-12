package extended;

import gtnative.GT;

import com.sun.jna.Pointer;
import com.sun.jna.TransparentPointer;

import core.Str;

public class SequenceNode extends GenomeNode {
  public SequenceNode(Pointer node_ptr) {
    super(node_ptr);
  }

  public SequenceNode(SequenceNode node) {
    super(node.to_ptr());
  }

  public SequenceNode(String description, String sequence) {
    if (description == null || sequence == null) {
      throw new NullPointerException(
          "trying to pass null description or sequence");
    }
    Str s = new Str(sequence);
    Pointer newfn = GT.INSTANCE.gt_sequence_node_new(description, s.to_ptr());
    this.genome_node_ptr = new TransparentPointer(newfn);
  }

  public String get_description() {
    return GT.INSTANCE.gt_sequence_node_get_description(this.genome_node_ptr);
  }

  public String get_sequence() {
    String s = GT.INSTANCE.gt_sequence_node_get_sequence(this.genome_node_ptr);
    if (s == null) {
      s = "";
    }
    return s;
  }

  public long get_sequence_length() {
    return GT.INSTANCE.gt_sequence_node_get_sequence_length(
        this.genome_node_ptr).longValue();
  }
}
