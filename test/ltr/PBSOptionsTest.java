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

package ltr;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import core.GTerrorJava;

public class PBSOptionsTest extends TestCase {
  private PBSOptions opts;

  @Before
  public void setUp() throws Exception {
    String trnalibfile = this.getClass().getResource(
        "testdata/Dm-tRNAs-uniq.fa").getPath();
    opts = new PBSOptions(trnalibfile);
  }

  @Test
  public void test_invalid_trna_lib() throws GTerrorJava {
    try {
      new PBSOptions("nonexistant");
      fail();
    } catch (GTerrorJava e) {
      return;
    }
  }
  
  @Test
  public void test_null_trna_lib() throws GTerrorJava{
    try {
      new PBSOptions((String) null);
      fail();
    } catch (NullPointerException e) {
      return;
    }
  }
  
  @Test
  public void test_radius() throws GTerrorJava {
    opts.set_radius(42);
    assertEquals(opts.radius, 42);
    try {
      opts.set_radius(-34);
      fail();
    } catch (GTerrorJava e) {
      // pass
    }
    try {
      opts.set_radius(0);
      fail();
    } catch (GTerrorJava e) {
      // pass
    }
  }
  
  @Test
  public void test_maxedist() throws GTerrorJava {
    opts.set_max_edist(42);
    assertEquals(opts.max_edist, 42);
    try {
      opts.set_max_edist(-34);
      fail();
    } catch (GTerrorJava e) {
      return;
    }
  }
  
  @Test
  public void test_alilen() throws GTerrorJava {
    opts.set_alignment_length(23, 42);
    assertEquals(opts.alilen.start.longValue(), 23);
    assertEquals(opts.alilen.end.longValue(), 42);
    try {
      opts.set_alignment_length(42, 15);
      fail();
    } catch (GTerrorJava e) {
      return;
    }
  }
  
  @Test
  public void test_pbsoffsetlen() throws GTerrorJava {
    opts.set_pbs_offset_length(23, 42);
    assertEquals(opts.offsetlen.start.longValue(), 23);
    assertEquals(opts.offsetlen.end.longValue(), 42);
    try {
      opts.set_pbs_offset_length(42, 15);
      fail();
    } catch (GTerrorJava e) {
      return;
    }
  }
  
  @Test
  public void test_trnaoffsetlen() throws GTerrorJava {
    opts.set_trna_offset_length(23, 42);
    assertEquals(opts.trnaoffsetlen.start.longValue(), 23);
    assertEquals(opts.trnaoffsetlen.end.longValue(), 42);
    try {
      opts.set_trna_offset_length(42, 15);
      fail();
    } catch (GTerrorJava e) {
      return;
    }
  }
  
}
