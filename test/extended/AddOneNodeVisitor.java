/*
  Copyright (c) 2010 Sascha Steinbiss <steinbiss@zbh.uni-hamburg.de>
  Copyright (c) 2010 Center for Bioinformatics, University of Hamburg

  Permission to use, copy, modify, and distribute this software for any
  purpose with or without fee is hereby granted, provided that the above
  copyright notice and this permission notice appear in all copies.

  THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
  WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
  MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
  ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
  WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
  ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
  OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
*/


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
