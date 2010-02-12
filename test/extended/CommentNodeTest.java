package extended;

import org.junit.Test;

import junit.framework.TestCase;

public class CommentNodeTest extends TestCase {
  static String testString = "foo";

  @Test
  public void test_create() throws Exception {
    new CommentNode(testString);
  }

  @Test
  public void test_create_fail() {
    try {
      new CommentNode((String) null);
      fail("expected NullPointerException");
    } catch (NullPointerException e) {
      return;
    }
  }

  @Test
  public void test_get_comment() throws Exception {
    assertEquals((new CommentNode(testString)).get_comment(), testString);
  }
}
