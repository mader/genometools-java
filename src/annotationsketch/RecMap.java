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

import extended.FeatureNode;

public class RecMap {
  protected Pointer rec_map;

  public RecMap(Pointer rm) {
    synchronized (rm) {
      this.rec_map = GT.INSTANCE.gt_rec_map_ref(rm);
    }
  }

  public synchronized void finalize() {
    GT.INSTANCE.gt_rec_map_delete(rec_map);
  }

  public double get_northwest_x() {
    return GT.INSTANCE.gt_rec_map_get_northwest_x(rec_map);
  }

  public double get_northwest_y() {
    return GT.INSTANCE.gt_rec_map_get_northwest_y(rec_map);
  }

  public double get_southeast_x() {
    return GT.INSTANCE.gt_rec_map_get_southeast_x(rec_map);
  }

  public double get_southeast_y() {
    return GT.INSTANCE.gt_rec_map_get_southeast_y(rec_map);
  }

  public FeatureNode get_genome_feature() {
    return new FeatureNode(GT.INSTANCE.gt_rec_map_get_genome_feature(rec_map));
  }

  public boolean has_omitted_children() {
    return (GT.INSTANCE.gt_rec_map_has_omitted_children(rec_map) == 1);
  }
}
