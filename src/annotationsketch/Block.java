package annotationsketch;

import gtnative.GT;
import com.sun.jna.Pointer;
import core.GTerrorJava;
import core.Range;
import extended.FeatureNode;

public class Block
{
  private Pointer block_ptr;
  private final char STRANDCHARS[] = { '+', '-', '.', '?' };

  public Block(Pointer ptr) throws GTerrorJava
  {
    if (ptr == null) {
      throw new GTerrorJava("block pointer must not be NULL");
    }
    synchronized(this) {
      block_ptr = GT.INSTANCE.gt_block_ref(ptr);
    }
  }

  public Range get_range()
  {
    Range r = new Range();
    r = GT.INSTANCE.gt_block_get_range_ptr(this.block_ptr);
    return r;
  }

  public String get_type()
  {
    return GT.INSTANCE.gt_block_get_type(this.block_ptr);
  }

  public Boolean has_only_one_fullsize_element()
  {
    return (GT.INSTANCE.gt_block_has_only_one_fullsize_element(this.block_ptr) == 1);
  }

  public void merge(Block block2)
  {
    GT.INSTANCE.gt_block_merge(this.block_ptr, block2.to_ptr());
  }

  public Block clone_block() throws GTerrorJava
  {
    return new Block(GT.INSTANCE.gt_block_clone(block_ptr));
  }

  public void set_strand(char strand) throws GTerrorJava
  {
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

  public char get_strand()
  {
    return STRANDCHARS[GT.INSTANCE.gt_block_get_strand(block_ptr)];
  }

  public synchronized FeatureNode get_top_level_feature()
  {
    Pointer f = GT.INSTANCE.gt_block_get_top_level_feature(block_ptr);
    if (f != null) {
      return new FeatureNode(f);
    } else {
      return null;
    }
  }

  public long get_size()
  {
    return GT.INSTANCE.gt_block_get_size(block_ptr).longValue();
  }

  public Pointer to_ptr()
  {
    return block_ptr;
  }

  protected void finalize() throws Throwable {
    try {
        GT.INSTANCE.gt_block_delete(block_ptr);
    } finally {
      super.finalize();
    }
  }
}
