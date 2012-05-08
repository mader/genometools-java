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
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import core.GTerror;
import core.GTerrorJava;

import extended.FeatureNode;

public class FeatureIndexMemory extends FeatureIndex {

  public FeatureIndexMemory() {
    super(GT.INSTANCE.gt_feature_index_memory_new());
    set_disposed(false);
  }

  public FeatureNode get_node_by_ptr(long id) throws GTerrorJava {
    GTerror err = new GTerror();
    NativeLong id_long = new NativeLong(id);
    Pointer tmp = GT.INSTANCE.gt_feature_index_memory_get_node_by_ptr(
        this.feat_index, id_long, err.to_ptr());
    FeatureNode feat;
    if (tmp == Pointer.NULL) {
      throw new GTerrorJava(err.get_err());
    } else {
      feat = new FeatureNode(tmp);
    }
    return feat;
  }

  protected synchronized void finalize() throws Throwable {
    super.finalize();
  }
}