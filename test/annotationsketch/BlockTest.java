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
import annotationsketch.TrackSelector;
import annotationsketch.Diagram;
import annotationsketch.Layout;
import static org.junit.Assert.*;

public class BlockTest
{
  static Style s;
  static TrackSelector tsf;
  static ArrayList<Block> blist = new ArrayList<Block>();
  static FeatureNode top1, top2;
  static Diagram d;
  static Layout l;

  @BeforeClass
  public static void init() throws GTerrorJava {
    s = new Style();
    s.set_bool("exon", "collapse_to_parent", true);
    s.set_bool("intron", "collapse_to_parent", true);
    String seqid = "foo";
    FeatureNode gene = new FeatureNode(seqid, "gene", 100, 900, "+");
    top1 = gene;
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
    top2 = reverse_gene;
    ArrayList<FeatureNode> feats = new ArrayList<FeatureNode>();
    feats.add(gene);
    feats.add(reverse_gene);
    Range rng = new Range();
    rng.set_start(0);
    rng.set_end(1000);
    // TSF which also collects blocks in an external list
    tsf = new TrackSelector() {
      public String getTrackId(Block b) {
        blist.add(b);
        return b.get_type();
      }
    };
    d = new Diagram(feats, rng, s);
    d.set_track_selector_func(tsf);
    l = new Layout(d, 800, s);
  }

  @AfterClass
  static public void tearDown() {
    l.dispose();
    d.dispose();
    s.dispose();
    top1.dispose();
    top2.dispose();
  }

  
  @Test
  public void test_get_type() {
    assertTrue(blist.get(0).get_type().equals("gene"));
    assertTrue(blist.get(1).get_type().equals("gene"));
  }

  @Test
  public void test_get_range() {
    Range r = blist.get(0).get_range();
    assertTrue(r.get_start() == 100);
    assertTrue(r.get_end() == 900);
    r = blist.get(1).get_range();
    assertTrue(r.get_start() == 400);
    assertTrue(r.get_end() == 600);
  }

  @Test
  public void test_get_size() {
    // 4 exons, 1 intron, 1 gene
    assertTrue(blist.get(0).get_size() == 6);
    // 1 gene
    assertTrue(blist.get(1).get_size() == 1);
  }

  @Test
  public void test_has_only_one_fullsize_element() {
    assertFalse(blist.get(0).has_only_one_fullsize_element());
    assertTrue(blist.get(1).has_only_one_fullsize_element());
  }

  @Test
  public void test_clone_block() throws GTerrorJava {
    Block b = blist.get(0).clone_block();
    assertTrue(blist.get(0).get_size() == b.get_size());
    b = blist.get(1).clone_block();
    assertTrue(blist.get(1).get_size() == b.get_size());
    b.delete();
  }

  @Test
  public void test_get_strand() {
    assertTrue(blist.get(0).get_strand() == '+');
    assertTrue(blist.get(1).get_strand() == '-');
  }

  @Test
  public void test_set_strand() throws GTerrorJava {
    Block b;
    b = blist.get(0).clone_block();
    assertTrue(b.get_strand() == '+');
    b.set_strand('?');
    assertTrue(b.get_strand() == '?');
    b.delete();
    b = blist.get(1).clone_block();
    assertTrue(b.get_strand() == '-');
    b.set_strand('.');
    assertTrue(b.get_strand() == '.');
    b.delete();
  }

  @Test(expected=GTerrorJava.class)
  public void test_set_strand_fail() throws GTerrorJava {
    Block b = blist.get(0).clone_block();
    b.set_strand('X');
    b.delete();
  }
}

