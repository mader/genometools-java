/*
  Copyright (c) 2009 Philipp Carpus  <random234@gmx.net>
  Copyright (c) 2009 Center for Bioinformatics, University of Hamburg

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


package annotationsketch;

import java.util.ArrayList;

import org.junit.*;

import core.GTerrorJava;
import core.Range;
import extended.FeatureNode;
import static org.junit.Assert.*;

public class FeatureIndexTest
{
  static FeatureIndexMemory fi;
  static ArrayList<FeatureNode> feats;

  @BeforeClass
  public static void init() throws GTerrorJava {
    fi =  new FeatureIndexMemory();
    feats = new ArrayList<FeatureNode>();
    String seqid = "foo";
    FeatureNode gene = new FeatureNode(seqid, "gene", 100, 900, "+");
    FeatureNode exon = new FeatureNode(seqid, "exon", 100, 200, "+");
    gene.add_child(exon);
    FeatureNode intron = new FeatureNode(seqid, "intron", 201, 799, "+");
    gene.add_child(intron);
    FeatureNode exon2 = new FeatureNode(seqid, "exon", 800, 900, "+");
    FeatureNode exon3 = new FeatureNode(seqid, "exon", 850, 900, "-");
    FeatureNode exon4 = new FeatureNode(seqid, "exon", 50, 150, "?");
    gene.add_child(exon2);
    gene.add_child(exon3);
    gene.add_child(exon4);
    FeatureNode reverse_gene = new FeatureNode(seqid, "gene", 400, 600, "-");
    fi.add_feature_node(gene);
    feats.add(gene);
    fi.add_feature_node(reverse_gene);
    feats.add(reverse_gene);
  }

  @AfterClass
  static public void tearDown() {
    for(int i=0; i < feats.size(); i++){
      feats.get(i).dispose();
    }
    fi.dispose();
  }
  
  @Test
  public void test_get_first_seqid() {
    try {
		assertTrue(fi.get_first_seqid().equals("foo"));
	} catch (GTerrorJava e) {
		e.printStackTrace();
	}
  }

  @Test
  public void test_get_seqids() {
   ArrayList<String> arr = null;
  try {
	arr = fi.get_seqids();
  } catch (GTerrorJava e) {
	e.printStackTrace();
  }
   assertTrue(arr.size() == 1);
   assertTrue(arr.get(0).equals("foo"));
  }

  @Test
  public void test_get_range_for_seqid() throws GTerrorJava {
    Range r = new Range();
    r = fi.get_range_for_seqid("foo");
    assertTrue(r.get_start() == 100);
    assertTrue(r.get_end() == 900);
    assertTrue(r.length() == 801);
  }

  @Test
  public void test_get_features_for_seqid() {
    ArrayList<FeatureNode> nodes = null;
    try {
		nodes = fi.get_features_for_seqid("foo");
	} catch (GTerrorJava e) {
		e.printStackTrace();
	}
    assertTrue(nodes.size() == 2);
    assertTrue(nodes.size() == feats.size());
    // check mutual inclusion of both lists (set equality)
    for (FeatureNode n : nodes) {
      assertTrue(feats.contains(n));
    }
    for (FeatureNode n : feats) {
      assertTrue(nodes.contains(n));
    }
  }

  @Test
  public void test_to_ptr() {
    assertTrue(fi.to_ptr() == fi.feat_index);
  }
}
