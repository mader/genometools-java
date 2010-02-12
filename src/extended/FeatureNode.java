/*
  Copyright (c) 2009      Philipp Carpus  <random234@gmx.net>
  Copyright (c) 2009-2010 Sascha Steinbiss <steinbiss@zbh.uni-hamburg.de>
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


package extended;

import java.util.ArrayList;

import com.sun.jna.*;
import gtnative.GT;

import core.GTerrorJava;
import core.Str;
import core.StrArray;
import extended.GenomeNode;

public class FeatureNode extends GenomeNode {
  private static char[] STRANDCHARS = { '+', '-', '.', '?' };

  public FeatureNode(Pointer node_ptr) {
    super(node_ptr);
  }

  public FeatureNode(FeatureNode node) {
    super(node.to_ptr());
  }

  public FeatureNode(String seqid, String type, int start, int end, String stra)
      throws GTerrorJava {
    Pointer newfn;
    char strand = stra.charAt(0);
    switch (strand) {
    case '+':
      strand = 0;
      break;
    case '-':
      strand = 1;
      break;
    case '.':
      strand = 2;
      break;
    case '?':
      strand = 3;
      break;
    default:
      throw new GTerrorJava("Invalid Strand " + (char) strand
          + " must be one of: [+ - . ?]");
    }
    Str s = new Str(seqid);
    NativeLong stmp = new NativeLong(start);
    NativeLong etmp = new NativeLong(end);
    synchronized (this) {
      newfn = GT.INSTANCE.gt_feature_node_new(s.to_ptr(), type, stmp, etmp,
          strand);
      this.genome_node_ptr = new TransparentPointer(newfn);
    }
  }

  public void add_child(FeatureNode child) {
    GT.INSTANCE.gt_feature_node_add_child(this.genome_node_ptr, child.to_ptr());
  }

  public String get_source() {
    return GT.INSTANCE.gt_feature_node_get_source(this.genome_node_ptr);
  }

  public void set_source(Pointer source) {
    GT.INSTANCE.gt_feature_node_set_source(this.genome_node_ptr, source);
  }

  public String get_type() {
    return GT.INSTANCE.gt_feature_node_get_type(this.genome_node_ptr);
  }

  public Boolean has_type(String type) {
    return (GT.INSTANCE.gt_feature_node_has_type(this.genome_node_ptr, type) == 1);
  }

  public Boolean score_is_defined() {
    return (GT.INSTANCE.gt_feature_node_score_is_defined(this.genome_node_ptr) != 0);
  }

  public float get_score() {
    if (this.score_is_defined() == true) {
      return GT.INSTANCE.gt_feature_node_get_score(this.genome_node_ptr);
    } else {
      return (float) 0;
    }
  }

  public void set_score(float score) {
    GT.INSTANCE.gt_feature_node_set_score(this.genome_node_ptr, score);
  }

  public void unset_score() {
    GT.INSTANCE.gt_feature_node_unset_score(this.genome_node_ptr);
  }

  public char get_strand() {
    return STRANDCHARS[GT.INSTANCE
        .gt_feature_node_get_strand(this.genome_node_ptr)];
  }

  public void set_strand(String stra) throws GTerrorJava {
    char strand = stra.charAt(0);
    switch (strand) {
    case '+':
      strand = 0;
      break;
    case '-':
      strand = 1;
      break;
    case '.':
      strand = 2;
      break;
    case '?':
      strand = 3;
    default:
      throw new GTerrorJava("Invalid Strand " + (char) strand
          + " must be one of: [+ - . ?]");
    }
    GT.INSTANCE.gt_feature_node_set_strand(genome_node_ptr, strand);
  }

  public int get_phase() {
    return GT.INSTANCE.gt_feature_node_get_phase(this.genome_node_ptr);
  }

  public void set_phase(int strand) {
    GT.INSTANCE.gt_feature_node_set_phase(this.genome_node_ptr, strand);
  }

  public String get_attribute(String name) {
    return GT.INSTANCE
        .gt_feature_node_get_attribute(this.genome_node_ptr, name);
  }

  public void add_attribute(String tag, String value) throws GTerrorJava {
    if (tag.toString() == "" || value.toString() == "") {
      throw new GTerrorJava("attribute keys or values must not be empty");
    } else {
      GT.INSTANCE.gt_feature_node_add_attribute(this.genome_node_ptr, tag,
          value);
    }
  }

  public ArrayList<String> get_attribute_list() {
    StrArray str_arr = new StrArray(GT.INSTANCE
        .gt_feature_node_get_attribute_list(genome_node_ptr));
    ArrayList<String> arr = new ArrayList<String>();
    for (int i = 0; i < str_arr.length(); i++) {
      arr.add(str_arr.get(i));
    }
    return arr;
  }

  public void mark() {
    GT.INSTANCE.gt_genome_node_mark(this.genome_node_ptr);
  }

  public boolean is_marked() {
    return (GT.INSTANCE.gt_genome_node_is_marked(this.genome_node_ptr) == 1);
  }
}
