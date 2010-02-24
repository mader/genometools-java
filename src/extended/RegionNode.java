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

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.TransparentPointer;

import core.GTerrorJava;
import core.Str;

public class RegionNode extends GenomeNode {
  public RegionNode(Pointer node_ptr) {
    super(node_ptr);
  }

  public RegionNode(RegionNode node) {
    super(node.to_ptr());
  }

  public RegionNode(String seqid, long start, long end)
      throws NullPointerException, GTerrorJava {
    if (seqid == null) {
      throw new NullPointerException("trying to pass null as seqid");
    }
    if (start > end) {
      throw new GTerrorJava("start position (" + start
          + ") is after end position (" + end + ")");
    }
    Str s = new Str(seqid);
    NativeLong nl_start = new NativeLong(start), nl_end = new NativeLong(end);
    Pointer newfn = GT.INSTANCE
        .gt_region_node_new(s.to_ptr(), nl_start, nl_end);
    this.genome_node_ptr = new TransparentPointer(newfn);
    set_disposed(false);
  }
}
