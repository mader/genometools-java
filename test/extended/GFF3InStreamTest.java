package extended;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import core.GTerrorJava;

public class GFF3InStreamTest {
  static File tmpfile;
  static String gffcontent = "##gff-version 3\n"
      + "##sequence-region 1 1 500\n"
      + "1\tucb\tgene\t100\t200\t.\t-\t.\tID=geneA\n"
      + "1\tucb\tgene\t300\t400\t.\t+\t.\tID=geneB";

  @BeforeClass
  public static void setUpBeforeClass() throws IOException {
    tmpfile = File.createTempFile("gff3test_temp", ".gff3");
    tmpfile.deleteOnExit();
    BufferedWriter out = new BufferedWriter(new FileWriter(tmpfile));
    out.write(gffcontent);
    out.close();
  }

  @Test
  public void test_in_stream() throws IOException, GTerrorJava {
    GFF3InStream i = new GFF3InStream(tmpfile.getCanonicalPath());
    GenomeNode n;
    ArrayList<GenomeNode> result_nodes = new ArrayList<GenomeNode>();
    while ((n = i.next_tree()) != null) {
      result_nodes.add(n);
    }
    assertTrue(result_nodes.size() == 2);
  }
  
  @Test(expected=IOException.class)
  public void test_in_stream_failure_nonexistant_file() throws IOException {
    new GFF3InStream("");
  }
}
