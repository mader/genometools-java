package ltr;

import junit.framework.TestCase;

import org.junit.Test;

import core.StrArray;
import extended.GFF3InStream;
import extended.GenomeStream;

public class LTRdigestStreamTest extends TestCase {
  String gff3filename = this.getClass().getResource("testdata/dmel_test_Run9_4.gff3").getPath();
  String indexname = this.getClass().getResource("testdata/4_genomic_dmel_RELEASE3-1.FASTA.gz").getPath();
  String trnalibfile = this.getClass().getResource("testdata/Dm-tRNAs-uniq.fa").getPath();
  String[] arr = { this.getClass().getResource("testdata/RVT_1_fs.hmm").getPath() };

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
