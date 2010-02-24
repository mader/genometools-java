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

import junit.framework.TestCase;

public class SequenceNodeTest extends TestCase {
  static String testDesc = "description";
  static String testSequence = "GTACGGATGCGTGATGCTGTGGGGATTAATATTGCTGTGATC";

  @Test
  public void test_create() {
    new SequenceNode(testDesc, testSequence).dispose();
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
    sn.dispose();
  }

  @Test
  public void test_get_sequence() {
    SequenceNode sn = new SequenceNode(testDesc, testSequence);
    assertEquals(sn.get_sequence(), testSequence);
    sn.dispose();
  }
}
