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

import gtnative.GT;

import com.sun.jna.Pointer;
import com.sun.jna.TransparentPointer;

import core.Str;

public class SequenceNode extends GenomeNode {
  public SequenceNode(Pointer node_ptr) {
    super(node_ptr);
  }

  public SequenceNode(SequenceNode node) {
    super(node.to_ptr());
  }

  public SequenceNode(String description, String sequence) {
    if (description == null || sequence == null) {
      throw new NullPointerException(
          "trying to pass null description or sequence");
    }
    Str s = new Str(sequence);
    Pointer newfn = GT.INSTANCE.gt_sequence_node_new(description, s.to_ptr());
    this.genome_node_ptr = new TransparentPointer(newfn);
    set_disposed(false);
  }

  public String get_description() {
    return GT.INSTANCE.gt_sequence_node_get_description(this.genome_node_ptr);
  }

  public String get_sequence() {
    String s = GT.INSTANCE.gt_sequence_node_get_sequence(this.genome_node_ptr);
    if (s == null) {
      s = "";
    }
    return s;
  }

  public long get_sequence_length() {
    return GT.INSTANCE.gt_sequence_node_get_sequence_length(
        this.genome_node_ptr).longValue();
  }
}
