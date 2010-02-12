package extended;

import com.sun.jna.Pointer;

import core.GTerror;

import gtnative.GT;
import gtnative.GT.VISITORFUNC;

public abstract class CustomVisitor extends NodeVisitor {
  private VISITORFUNC comment_node_visit_func;
  private VISITORFUNC feature_node_visit_func;
  private VISITORFUNC region_node_visit_func;
  private VISITORFUNC sequence_node_visit_func;

  protected abstract void visit_comment_node(CommentNode cn) throws Exception;

  protected abstract void visit_feature_node(FeatureNode fn) throws Exception;

  protected abstract void visit_region_node(RegionNode rn) throws Exception;

  protected abstract void visit_sequence_node(SequenceNode sn) throws Exception;

  public CustomVisitor() {
    comment_node_visit_func = new VISITORFUNC() {
      @Override
      public int callback(Pointer nodep, Pointer errp) {
        GTerror err = new GTerror(errp);
        int rval = 0;
        try {
          CommentNode fn = new CommentNode(nodep);
          visit_comment_node(fn);
        } catch (Exception e) {
          err.set(e.toString());
          rval = -1;
        }
        return rval;
      }
    };
    feature_node_visit_func = new VISITORFUNC() {
      @Override
      public int callback(Pointer nodep, Pointer errp) {
        GTerror err = new GTerror(errp);
        int rval = 0;
        try {
          FeatureNode fn = new FeatureNode(nodep);
          visit_feature_node(fn);
        } catch (Exception e) {
          err.set(e.toString());
          rval = -1;
        }
        return rval;
      }
    };
    region_node_visit_func = new VISITORFUNC() {
      @Override
      public int callback(Pointer nodep, Pointer errp) {
        GTerror err = new GTerror(errp);
        int rval = 0;
        try {
          RegionNode fn = new RegionNode(nodep);
          visit_region_node(fn);
        } catch (Exception e) {
          err.set(e.toString());
          rval = -1;
        }
        return rval;
      }
    };
    sequence_node_visit_func = new VISITORFUNC() {
      @Override
      public int callback(Pointer nodep, Pointer errp) {
        GTerror err = new GTerror(errp);
        int rval = 0;
        try {
          SequenceNode fn = new SequenceNode(nodep);
          visit_sequence_node(fn);
        } catch (Exception e) {
          err.set(e.toString());
          rval = -1;
        }
        return rval;
      }
    };
    visitor = GT.INSTANCE.gt_script_wrapper_visitor_new(
        comment_node_visit_func, feature_node_visit_func,
        region_node_visit_func, sequence_node_visit_func, null);
  }
}
