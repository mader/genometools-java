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

import gtnative.GT;
import com.sun.jna.Pointer;
import core.GTerrorJava;
import core.Range;
import extended.FeatureNode;

public class Block {
  private Pointer block_ptr;
  private static char STRANDCHARS[] = { '+', '-', '.', '?' };
  private boolean deleted;

  public Block(Pointer ptr) {
    synchronized (this) {
      block_ptr = GT.INSTANCE.gt_block_ref(ptr);
    }
    deleted = false;
  }

  public Range get_range() {
    Range r = new Range();
    r = GT.INSTANCE.gt_block_get_range_ptr(this.block_ptr);
    return r;
  }

  public String get_type() {
    return GT.INSTANCE.gt_block_get_type(this.block_ptr);
  }

  public Boolean has_only_one_fullsize_element() {
    return (GT.INSTANCE.gt_block_has_only_one_fullsize_element(this.block_ptr) != 0);
  }

  public Block clone_block() throws GTerrorJava {
    return new Block(GT.INSTANCE.gt_block_clone(block_ptr));
  }

  public void set_strand(char strand) throws GTerrorJava {
    switch (strand) {
    case '+':
      GT.INSTANCE.gt_block_set_strand(block_ptr, 0);
      break;
    case '-':
      GT.INSTANCE.gt_block_set_strand(block_ptr, 1);
      break;
    case '.':
      GT.INSTANCE.gt_block_set_strand(block_ptr, 2);
      break;
    case '?':
      GT.INSTANCE.gt_block_set_strand(block_ptr, 3);
      break;
    default:
      throw new GTerrorJava("Invalid Strand " + (char) strand
          + " must be one of: [+ - . ?]");
    }
  }

  public char get_strand() {
    return STRANDCHARS[GT.INSTANCE.gt_block_get_strand(block_ptr)];
  }

  public synchronized FeatureNode get_top_level_feature() {
    Pointer f = GT.INSTANCE.gt_block_get_top_level_feature(block_ptr);
    if (f != null) {
      return new FeatureNode(f);
    } else {
      return null;
    }
  }

  public long get_size() {
    return GT.INSTANCE.gt_block_get_size(block_ptr).longValue();
  }

  public Pointer to_ptr() {
    return block_ptr;
  }

  public synchronized void delete() {
    if(!deleted){
      GT.INSTANCE.gt_block_delete(block_ptr);
      deleted = true;
    }
  }
}
