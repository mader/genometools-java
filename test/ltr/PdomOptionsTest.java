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

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import core.GTerrorJava;
import core.StrArray;

public class PdomOptionsTest extends TestCase {
  private PdomOptions opts;

  @Before
  public void setUp() throws Exception {
    String[] arr = { this.getClass().getResource("testdata/RVT_1_fs.hmm")
        .getPath() };
    StrArray pdomfiles = new StrArray(arr);
    opts = new PdomOptions(pdomfiles);
  }

  @Test
  public void test_setpdomfiles() throws IOException {
    try {
      opts.set_pdomfiles((StrArray) null);
      fail();
    } catch (NullPointerException e) {
      // pass
    }
    try {
      String[] arr = { "nonexistant" };
      opts.set_pdomfiles(new StrArray(arr));
      fail();
    } catch (IOException e) {
      // pass
    }
  }

  @Test
  public void test_nof_threads() throws GTerrorJava {
    opts.set_number_of_threads(3);
    assertEquals(opts.nof_threads, 3);
    try {
      opts.set_number_of_threads(-34);
      fail();
    } catch (GTerrorJava e) {
      // pass
    }
    try {
      opts.set_number_of_threads(0);
      fail();
    } catch (GTerrorJava e) {
      // pass
    }
  }

  @Test
  public void test_chain_max_gap_length() throws GTerrorJava {
    opts.set_chain_max_gap_length(30);
    assertEquals(opts.chain_max_gap_length, 30);
    try {
      opts.set_chain_max_gap_length(-34);
      fail();
    } catch (GTerrorJava e) {
      // pass
    }
  }

  @Test
  public void test_evalcutoff() throws GTerrorJava {
    opts.set_evalue_cutoff(.0004);
    assertEquals(opts.evalue_cutoff, .0004);
    try {
      opts.set_evalue_cutoff(-2);
      fail();
    } catch (GTerrorJava e) {
      // pass
    }
  }

  @Test
  public void test_hmmer_cutoffs() {
    opts.set_gathering_cutoff();
    assertEquals(opts.cutoff, 1);
    opts.set_trusted_cutoff();
    assertEquals(opts.cutoff, 0);
    opts.disable_cutoff();
    assertEquals(opts.cutoff, 2);

  }
}
