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

import java.util.ArrayList;
import junit.framework.TestCase;

import org.junit.Test;

import core.GTerrorJava;

public class AllNodeTypesVisitorTest extends TestCase {

  @Test
  public void test_accept() throws GTerrorJava {
    ArrayList<GenomeNode> nodes;
    nodes = new ArrayList<GenomeNode>();
    NodeVisitor testvisitor = new AllNodeTypesVisitor(nodes);
    
    RegionNode rn = new RegionNode("foo", 1, 1000);
    SequenceNode sn = new SequenceNode("desc", "sequence");
    CommentNode cn = new CommentNode("comment");
    FeatureNode fn = new FeatureNode("foo", "gene", 100, 300, "+");
    
    assertTrue(nodes.size() == 0);
    rn.accept(testvisitor);
    assertTrue(nodes.size() == 1);
    assertEquals(nodes.get(0), rn);
    sn.accept(testvisitor);
    assertTrue(nodes.size() == 2);
    assertEquals(nodes.get(1), sn);
    cn.accept(testvisitor);
    assertTrue(nodes.size() == 3);
    assertEquals(nodes.get(2), cn);
    fn.accept(testvisitor);
    assertTrue(nodes.size() == 4);
    assertEquals(nodes.get(3), fn);
  }

}
