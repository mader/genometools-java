package extended;

import com.sun.jna.*;
import gtnative.*;

abstract class FeatureNodeIterator {
  Pointer feat_ptr;

  public FeatureNode next() {
    Pointer ret = GT.INSTANCE.gt_feature_node_iterator_next(feat_ptr);
    if (ret != null) {
      return new FeatureNode(ret);
    }
    return null;
  }

  protected void finalize() {
    GT.INSTANCE.gt_feature_node_iterator_delete(feat_ptr);
  }
}