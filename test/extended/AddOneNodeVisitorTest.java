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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import core.GTerrorJava;

public class AddOneNodeVisitorTest {
  static FeatureNode node;
  static NodeVisitor testvisitor;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    node = new FeatureNode("test", "type", 1000, 8000, ".");
    testvisitor = new AddOneNodeVisitor("newtype");
  }

  @AfterClass
  public static void tearDown() {
    node.dispose();
    testvisitor.dispose();
  }

  @Test
  public void test_accept() throws GTerrorJava {
    FeatureNodeIteratorDirect fni = new FeatureNodeIteratorDirect(node);
    List<FeatureNode> childnodes = new ArrayList<FeatureNode>();
    FeatureNode n;
    while((n = fni.next()) != null) {
      childnodes.add(n);
    }
    assertTrue(childnodes.size() == 0);

    node.accept(testvisitor);

    fni.dispose();

    fni = new FeatureNodeIteratorDirect(node);
    childnodes = new ArrayList<FeatureNode>();
    while((n = fni.next()) != null) {
      childnodes.add(n);
    }
    assertTrue(childnodes.size() == 1);
    assertEquals(childnodes.get(0).get_seqid(), node.get_seqid());
    assertEquals(childnodes.get(0).get_type(), "newtype");

    fni.dispose();
  }
}
