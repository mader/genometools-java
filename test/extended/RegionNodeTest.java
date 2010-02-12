package extended;

import org.junit.Test;

import core.GTerrorJava;
import core.Range;

import junit.framework.TestCase;

public class RegionNodeTest extends TestCase {
  static String testString = "foo";

  @Test
  public void test_create() throws GTerrorJava {
    new RegionNode(testString, 100, 1000);
  }

  @Test
  public void test_create_fail_null_seqid() throws GTerrorJava {
    try {
      new RegionNode((String) null, 1, 2);
      fail("expected NullPointerException");
    } catch (NullPointerException e) {
      return;
    }
  }

  @Test
  public void test_create_fail_range() {
    try {
      new RegionNode(testString, 100, 1);
      fail("expected GtErrorJava due to invalid range");
    } catch (GTerrorJava e) {
      return;
    }
  }

  @Test
  public void test_get_range() throws GTerrorJava {
    RegionNode rn = new RegionNode(testString, 100, 1000);
    Range r = rn.get_range();
    assertEquals(r.get_start(), 100);
    assertEquals(r.get_end(), 1000);
  }

  @Test
  public void test_get_seqid() throws GTerrorJava {
    RegionNode rn = new RegionNode(testString, 100, 1000);
    assertEquals(((GenomeNode) rn).get_seqid(), testString);
  }
}
