package extended;

import org.junit.Test;

import junit.framework.TestCase;

public class SequenceNodeTest extends TestCase {
  static String testDesc = "description";
  static String testSequence = "GTACGGATGCGTGATGCTGTGGGGATTAATATTGCTGTGATC";

  @Test
  public void test_create() {
    new SequenceNode(testDesc, testSequence);
  }

  @Test
  public void test_create_fail_null_desc() {
    try {
      new SequenceNode((String) null, "foo");
      fail("expected NullPointerException");
    } catch (NullPointerException e) {
      return;
    }
  }

  @Test
  public void test_create_fail_null_seq() {
    try {
      new SequenceNode("foo", (String) null);
      fail("expected NullPointerException");
    } catch (NullPointerException e) {
      return;
    }
  }

  @Test
  public void test_get_description() {
    SequenceNode sn = new SequenceNode(testDesc, testSequence);
    assertEquals(sn.get_description(), testDesc);
  }

  @Test
  public void test_get_sequence() {
    SequenceNode sn = new SequenceNode(testDesc, testSequence);
    assertEquals(sn.get_sequence(), testSequence);
  }
}
