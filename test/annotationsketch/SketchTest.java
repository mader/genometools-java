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


package annotationsketch;

import java.io.File;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

import core.GTerrorJava;
import core.Range;
import core.Str;

import extended.FeatureNode;

public class SketchTest {
  static ArrayList<FeatureNode> arr;
  String tmpDir = System.getProperty("java.io.tmpdir");

  @BeforeClass
  public static void setUp() throws GTerrorJava {
    // construct a gene on the forward strand with two exons
    String seqid = "chromosome21";
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
    // construct a single-exon gene on the reverse strand
    // (within the intron of the forward strand gene)
    FeatureNode reverse_gene = new FeatureNode(seqid, "gene", 400, 600, "-");
    FeatureNode reverse_exon = new FeatureNode(seqid, "exon", 400, 600, "-");
    reverse_gene.add_child(reverse_exon);

    arr = new ArrayList<FeatureNode>();
    arr.add(gene);
    arr.add(reverse_gene);
  }
  
  @AfterClass
  static public void tearDown() {
    for(int i=0; i < arr.size(); i++){
      arr.get(i).dispose();
    }
  }

  @Test
  public void test_sketch() throws GTerrorJava {
    File f;
    Style sty = new Style();
    Range rng = new Range();
    rng.set_start(1);
    rng.set_end(1000);

    Diagram dia = new Diagram(arr, rng, sty);
    TrackSelector ts = new TrackSelector() {
      @Override
      public String getTrackId(Block b) {
        return b.get_type() + b.get_strand();
      }
    };
    dia.set_track_selector_func(ts);

    Layout lay = new Layout(dia, 800, sty);
    long height = lay.get_height();
    CanvasCairoFile can = new CanvasCairoFile(sty, 800, (int) height);
    lay.sketch(can);
    f = new File(tmpDir + File.separator + "test_sketch.png");
    if (f.exists())
      f.delete();
    can.to_file(tmpDir + File.separator + "test_sketch.png");
    f = new File(tmpDir + File.separator + "test_sketch.png");
    assertTrue(f.exists());
    assertTrue(f.length() > 0);
    f.delete();
    assertFalse(f.exists());
  }

  @Test
  public void test_multi_sketch() throws GTerrorJava {

    ArrayList<Thread> threads = new ArrayList<Thread>();

    class SketchThread extends Thread implements Runnable {
      public void run() {
        File f;
        long height;
        Style sty = null;
        try {
          sty = new Style();
          Range rng = new Range();
          rng.set_start(1);
          rng.set_end(1000);
          TrackSelector ts = new TrackSelector() {
            @Override
            public String getTrackId(Block b) {
              return b.get_type() + b.get_strand();
            }
          };
          Diagram dia = new Diagram(arr, rng, sty);

          dia.set_track_selector_func(ts);
          Layout lay = new Layout(dia, 800, sty);
          height = lay.get_height();
          CanvasCairoFile can = new CanvasCairoFile(sty, 800, (int) height);
          lay.sketch(can);
          String filename = tmpDir + File.separator + "test_sketch"
              + Math.random() * 10 + ".png";
          f = new File(filename);
          if (f.exists())
            f.delete();
          can.to_file(filename);
          assertTrue(f.exists());
          assertTrue(f.length() > 100);
          assertTrue(f.delete());
          assertFalse(f.exists());
          
          sty.dispose();
          dia.dispose();
          lay.dispose();
          can.dispose();
          
        } catch (GTerrorJava e) {
          throw new Error();
        }
      }
      
    }

    for (int i = 0; i < 100; i++) {
      SketchThread s = new SketchThread();
      threads.add(s);
      s.start();
    }

    for (Thread t : threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
      }
    }
  }

  @Test
  public void test_multi_sketch_serial() throws GTerrorJava {
    File f;
    Style sty = new Style();
    Range rng = new Range();
    rng.set_start(1);
    rng.set_end(1000);
    int i;
    TrackSelector ts = new TrackSelector() {
      @Override
      public String getTrackId(Block b) {
        return b.get_type() + b.get_strand();
      }
    };

    for (i = 0; i < 10; i++) {
      Diagram dia = new Diagram(arr, rng, sty);
      dia.set_track_selector_func(ts);

      Layout lay = new Layout(dia, 800, sty);
      long height = lay.get_height();
      ImageInfo ii = new ImageInfo();
      CanvasCairoFile can = new CanvasCairoFile(sty, 800, (int) height, ii);
      lay.sketch(can);
      f = new File(tmpDir + File.separator + "test_sketch" + i + ".png");
      if (f.exists())
        f.delete();
      can.to_file(tmpDir + File.separator + "test_sketch" + i + ".png");
      assertTrue(f.exists());
      assertTrue(f.length() > 0);
      f.delete();
      assertFalse(f.exists());
      
      dia.dispose();
      lay.dispose();
      ii.dispose();
      can.dispose(); 
    }
    sty.dispose();  
  }
  
  @Test
  public void test_multi_sketch_serial2() throws GTerrorJava {
    File f;
    Style sty = new Style();
    Range rng = new Range();
    rng.set_start(1);
    rng.set_end(1000);
    int i;
    OrderingFunction of = new OrderingFunction() {

	@Override
	public int cmp(String str1, String str2) {
		return str1.compareTo(str2);
	}
    };

    for (i = 0; i < 10; i++) {
      Diagram dia = new Diagram(arr, rng, sty);

      Layout lay = new Layout(dia, 800, sty);
      lay.set_track_ordering_func(of);
      long height = lay.get_height();
      ImageInfo ii = new ImageInfo();
      CanvasCairoFile can = new CanvasCairoFile(sty, 800, (int) height, ii);
      lay.sketch(can);
      f = new File(tmpDir + File.separator + "test_sketch" + i + ".png");
      if (f.exists())
        f.delete();
      can.to_file(tmpDir + File.separator + "test_sketch" + i + ".png");
      assertTrue(f.exists());
      assertTrue(f.length() > 0);
      f.delete();
      assertFalse(f.exists());
      
      dia.dispose();
      lay.dispose();
      ii.dispose();
      can.dispose(); 
    }
    sty.dispose();  
  }
}
