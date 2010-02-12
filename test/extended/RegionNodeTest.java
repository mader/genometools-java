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
