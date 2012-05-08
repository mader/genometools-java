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
import gtnative.GT;
import com.sun.jna.Pointer;
import com.sun.jna.Memory;
import core.GTerror;
import core.Array;
import core.GTerrorJava;
import core.Range;
import core.StrArray;
import extended.FeatureNode;

public abstract class FeatureIndex {
  protected Pointer feat_index;
  private boolean disposed;

  protected void set_disposed(boolean bool) {
    disposed = bool;
  }

  public synchronized void dispose() {
    if(!disposed){
      GT.INSTANCE.gt_feature_index_delete(feat_index);
      disposed = true;
    }
  }

  protected void finalize() throws Throwable {
    if(!disposed){
      dispose();
    }
  }

  public ArrayList<FeatureNode> get_features_for_seqid(String seqid) throws GTerrorJava {
    GTerror err = new GTerror();
    Pointer rval = GT.INSTANCE.gt_feature_index_get_features_for_seqid(
        this.feat_index, seqid, err.to_ptr());
    if (!(rval == null)) {
      Array a = new Array(rval);
      ArrayList<FeatureNode> results = new ArrayList<FeatureNode>();
      for (int i = 0; i < a.size(); i++) {
        FeatureNode fn = new FeatureNode(a.get(i));
        results.add(fn);
      }
      return results;
    } else {
      throw new GTerrorJava(err.get_err());
    }
  }

  public void add_feature_node(FeatureNode fn) throws GTerrorJava {
    int rval = 0;
    GTerror err = new GTerror();
    if (fn.to_ptr() == null)
      throw new GTerrorJava("feature node must not be NULL");
    rval = GT.INSTANCE.gt_feature_index_add_feature_node(this.feat_index,
                                                         fn.to_ptr(),
                                                         err.to_ptr());
    if (rval != 0) {
      throw new GTerrorJava(err.get_err());
    }
  }

  public void add_gff3file(String filename) throws GTerrorJava {
    GTerror err = new GTerror();
    int rval = GT.INSTANCE.gt_feature_index_add_gff3file(this.feat_index,
        filename, err.to_ptr());
    if (rval != 0) {
      throw new GTerrorJava(err.get_err());
    }
  }

  public String get_first_seqid() throws GTerrorJava {
    GTerror err = new GTerror();
    String rval = GT.INSTANCE.gt_feature_index_get_first_seqid(this.feat_index,
                                                               err.to_ptr());
    if (rval == null) {
      throw new GTerrorJava(err.get_err());
    }
    return rval;
  }

  public ArrayList<String> get_seqids() throws GTerrorJava {
    ArrayList<String> results = new ArrayList<String>();
    GTerror err = new GTerror();
    StrArray stra = new StrArray(GT.INSTANCE
        .gt_feature_index_get_seqids(this.feat_index, err.to_ptr()));
    if (stra == null) {
      throw new GTerrorJava(err.get_err());
    }
    for (int i = 0; i < stra.length(); i++) {
      results.add(stra.get(i));
    }
    return results;
  }

  public boolean has_seqid(String seqid) throws GTerrorJava {
    GTerror err = new GTerror();
    Pointer ret = new Memory(4); /* sizeof (int) */
    boolean out;
    int rval = GT.INSTANCE.gt_feature_index_has_seqid(this.feat_index, ret,
        seqid, err.to_ptr());
    if (rval != 0) {
      throw new GTerrorJava(err.get_err());
    }
    out = (ret.getInt(0) != 0);
    return out;
  }

  public Range get_range_for_seqid(String seqid) throws GTerrorJava {
    GTerror err = new GTerror();
    if (!this.has_seqid(seqid)) {
      throw new GTerrorJava("FeatureIndex does not contain seqid");
    }
    Range.ByReference ran = new Range.ByReference();
    int rval = GT.INSTANCE.gt_feature_index_get_range_for_seqid(this.feat_index,
        ran, seqid, err.to_ptr());
    if (rval != 0) {
      throw new GTerrorJava(err.get_err());
    }
    return ran;
  }

  public Pointer to_ptr() {
    return feat_index;
  }

}
