package extended;

public class AddOneNodeVisitor extends CustomVisitor {
  private String type;
   
  public AddOneNodeVisitor(String type) {
    this.type = type;
  }
  
  @Override
  protected void visit_feature_node(FeatureNode fn) throws Exception {
    fn.add_child(new FeatureNode(((GenomeNode) fn).get_seqid(), this.type, 100, 100, "-"));
  }

  @Override
  protected void visit_comment_node(CommentNode cn) throws Exception {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void visit_region_node(RegionNode rn) throws Exception {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void visit_sequence_node(SequenceNode sn) throws Exception {
    // TODO Auto-generated method stub
    
  }

}
