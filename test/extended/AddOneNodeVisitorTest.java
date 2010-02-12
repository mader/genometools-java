package extended;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
    
    fni = new FeatureNodeIteratorDirect(node);
    childnodes = new ArrayList<FeatureNode>();
    while((n = fni.next()) != null) {
      childnodes.add(n);
    }
    assertTrue(childnodes.size() == 1);
    assertEquals(childnodes.get(0).get_seqid(), node.get_seqid());
    assertEquals(childnodes.get(0).get_type(), "newtype");
  }
}
