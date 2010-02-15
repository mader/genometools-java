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

import core.StrArray;
import extended.GFF3InStream;
import extended.GenomeStream;

public class LTRdigestStreamTest extends TestCase {
  static String gff3filename;
  static String indexname;
  static String trnalibfile;
  static String[] arr;

  @Before
  public void setUp() {
    gff3filename = this.getClass().getResource(
        "testdata/dmel_test_Run9_4.gff3").getPath();
    indexname = this.getClass().getResource(
        "testdata/4_genomic_dmel_RELEASE3-1.FASTA.gz").getPath();
    trnalibfile = this.getClass().getResource(
        "testdata/Dm-tRNAs-uniq.fa").getPath();
    arr = new String[1];
    arr[0] = this.getClass().getResource("testdata/RVT_1_fs.hmm")
        .getPath();
  }

  @Test
  public void test_ltrdigest_stream_create() throws Exception {
    GenomeStream in_stream = new GFF3InStream(gff3filename);

    StrArray pdomfiles = new StrArray(arr);
    PBSOptions pbs_opts = new PBSOptions(trnalibfile);
    PPTOptions ppt_opts = new PPTOptions();
    PdomOptions pdom_opts = new PdomOptions(pdomfiles);

    GenomeStream ltrd_stream = new LTRdigestStream(in_stream, indexname,
        pbs_opts, ppt_opts, pdom_opts);

    int i = 0;
    while (ltrd_stream.next_tree() != null) {
      i++;
    }
    assertEquals(i, 13); // 11 features, region, comment
  }
}
