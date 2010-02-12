/*
  Copyright (c) 2009      Philipp Carpus  <random234@gmx.net>
  Copyright (c)      2010 Sascha Steinbiss <steinbiss@zbh.uni-hamburg.de>
  Copyright (c) 2009-2010 Center for Bioinformatics, University of Hamburg

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

import org.junit.*;

import core.GTerrorJava;
import core.Range;
import static org.junit.Assert.*;

public class FeatureNodeTest {
  static FeatureNode fn;
  static FeatureNode fn2;

  @BeforeClass
  public static void setUp() throws GTerrorJava {
    fn = new FeatureNode("test", "type", 1000, 8000, ".");
    fn2 = new FeatureNode("test", "type2", 600, 700, "+");
    fn.add_attribute("test", "testval");
    fn.add_attribute("test2", "testval");
  }

  @Test
  public void test_score_defined() {
    assertFalse(fn.score_is_defined());
    fn.set_score(2);
    assertTrue(fn.score_is_defined());
  }

  @Test
  public void test_get_score() {
    fn.set_score(2);
    assertTrue(fn.get_score() == 2);
  }

  @Test
  public void test_unset_score() {
    fn.unset_score();
    assertTrue(!fn.score_is_defined());
  }

  @Test
  public void test_has_type() {
    assertTrue(!fn.has_type("Horst"));
    assertTrue(fn.has_type("type"));
  }

  @Test
  public void test_get_strand() {
    assertTrue(fn.get_strand() == '.');
  }

  @Test
  public void test_set_strand() throws GTerrorJava{
    fn.set_strand("+");
    assertTrue(fn.get_strand() == '+');
  }

  @Test(expected=GTerrorJava.class)
  public void test_set_strand_failure() throws GTerrorJava {
    fn.set_strand("X");
  }

  @Test
  public void test_get_phase() {
    assertTrue(fn.get_phase() == 3);
  }

  @Test
  public void test_set_phase() {
    fn.set_phase(0);
    assertTrue(fn.get_phase() == 0);
  }

  @Test
  public void test_get_source() {
    assertTrue(fn.get_source().equals("."));
  }

  @Test
  public void test_get_type() {
    assertTrue(fn.get_type().equals("type"));
    assertTrue(fn2.get_type().equals("type2"));
  }

  @Test
  public void test_get_attribute() {
    assertTrue(fn.get_attribute("test").equals("testval"));
  }

  @Test
  public void test_get_range() {
    Range r = new Range();
    r = fn.get_range();
    assertTrue(r.length() == 7001);
  }

  @Test
  public void test_get_filename() {
    assertTrue(fn.get_filename().equals("generated"));
  }

  @Test
  public void test_get_seqid() {
    assertEquals(fn.get_seqid(), "test");
  }

  @Test
  public void test_equals() throws GTerrorJava {
    FeatureNode falseclone = new FeatureNode("test", "type", 1000, 8000, ".");
    assertTrue(fn.equals(fn));
    assertTrue(fn2.equals(fn2));
    FeatureNode clone = new FeatureNode(fn);
    assertTrue(fn.equals(clone));
    assertTrue(clone.equals(fn));
    assertFalse(fn.equals(falseclone));
    assertFalse(falseclone.equals(fn));
  }

  @Test
  public void test_genome_node_mark() throws GTerrorJava {
    assertFalse(fn.is_marked());
    fn.mark();
    assertTrue(fn.is_marked());
  }
}
