package extended;

import gtnative.GT;

import com.sun.jna.Pointer;
import com.sun.jna.TransparentPointer;

public class CommentNode extends GenomeNode {

  public CommentNode(Pointer node_ptr) {
    super(node_ptr);
  }

  public CommentNode(CommentNode node) {
    super(node.to_ptr());
  }
  
  public CommentNode(String comment) {
    Pointer newfn = GT.INSTANCE.gt_comment_node_new(comment);
    this.genome_node_ptr = new TransparentPointer(newfn);
  }
  
  public String get_comment() {
    return GT.INSTANCE.gt_comment_node_get_comment(genome_node_ptr);
  }
}
