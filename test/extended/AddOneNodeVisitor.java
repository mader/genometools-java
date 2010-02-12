package extended;

import core.GTerrorJava;

public class AddOneNodeVisitor extends CustomVisitor {
  private String type;
   
  public AddOneNodeVisitor(String type) {
    this.type = type;
  }
  
  @Override
  protected void visit_feature_node(FeatureNode fn) throws GTerrorJava {
    fn.add_child(new FeatureNode(((GenomeNode) fn).get_seqid(), this.type, 100, 100, "-"));
  }

}
