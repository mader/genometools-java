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
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import core.GTerrorJava;

public class ListStreamTest {
  private static ArrayList<GenomeNode> nodes;
  private static GenomeNode fn, fn2;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    nodes = new ArrayList<GenomeNode>();
    fn  = (GenomeNode) new FeatureNode("test", "type", 1000, 8000, ".");
    fn2 = (GenomeNode) new FeatureNode("test", "type2", 600, 700, "+");
    nodes.add(fn2);
    nodes.add(fn);
  }
  
  @AfterClass
  public static void tearDown() {
    fn.dispose();
    fn2.dispose();
  }
  
  @Test
  public void test_list_stream_array_list() throws GTerrorJava {
    GenomeStream s = new ListStream(nodes);
    GenomeNode n;
    ArrayList<GenomeNode> result_nodes = new ArrayList<GenomeNode>();
    while((n = s.next_tree()) != null) {
      result_nodes.add(n);
    }
    assertTrue(result_nodes.equals(nodes));
    s.dispose();
  }

}
