package extended;

import com.sun.jna.Pointer;

import gtnative.GT;

public abstract class NodeVisitor {
  protected Pointer visitor;

  protected synchronized void finalize() {
    GT.INSTANCE.gt_node_visitor_delete(this.visitor);
  }
  
  public Pointer to_ptr() {
    return visitor;
  }
}
