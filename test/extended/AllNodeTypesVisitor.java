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

import java.util.List;

public class AllNodeTypesVisitor extends CustomVisitor {
  private List<GenomeNode> collect_list;

  public AllNodeTypesVisitor(List<GenomeNode> collect_list) {
    this.collect_list = collect_list;
  }

  @Override
  protected void visit_feature_node(FeatureNode fn) throws Exception {
    this.collect_list.add(fn);
  }

  @Override
  protected void visit_comment_node(CommentNode cn) throws Exception {
    this.collect_list.add(cn);
  }

  @Override
  protected void visit_region_node(RegionNode rn) throws Exception {
    this.collect_list.add(rn);
  }

  @Override
  protected void visit_sequence_node(SequenceNode sn) throws Exception {
    this.collect_list.add(sn);
  }

}
