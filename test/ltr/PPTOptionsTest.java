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

public class PPTOptionsTest extends TestCase {
  private PPTOptions opts;

  @Before
  public void setUp() throws Exception {
    opts = new PPTOptions();
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
  public void test_pptlen() throws GTerrorJava {
    opts.set_ppt_length(23, 42);
    assertEquals(opts.ppt_len.start.longValue(), 23);
    assertEquals(opts.ppt_len.end.longValue(), 42);
    try {
      opts.set_ppt_length(42, 15);
      fail();
    } catch (GTerrorJava e) {
      return;
    }
  }
  
  @Test
  public void test_uboxlen() throws GTerrorJava {
    opts.set_ubox_length(23, 42);
    assertEquals(opts.ubox_len.start.longValue(), 23);
    assertEquals(opts.ubox_len.end.longValue(), 42);
    try {
      opts.set_ubox_length(42, 15);
      fail();
    } catch (GTerrorJava e) {
      return;
    }
  }
  
  @Test
  public void test_bkg_probs() throws GTerrorJava {
    opts.set_bkg_probabilities(.3, .2, .4, .1);
    assertEquals(opts.bkg_a_prob, .3);
    assertEquals(opts.bkg_c_prob, .2);
    assertEquals(opts.bkg_g_prob, .4);
    assertEquals(opts.bkg_t_prob, .1);
    try {
      opts.set_bkg_probabilities(1,2,3,4);
      fail();
    } catch (GTerrorJava e) {
      return;
    }
  }
  
  @Test
  public void test_ppt_probs() throws GTerrorJava {
    opts.set_ppt_probabilities(.7, .3);
    assertEquals(opts.ppt_purine_prob, .7);
    assertEquals(opts.ppt_pyrimidine_prob, .3);
    try {
      opts.set_ppt_probabilities(.7, .7);
      fail();
    } catch (GTerrorJava e) {
      return;
    }
  }
}
