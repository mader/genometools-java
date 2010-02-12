package annotationsketch;

import java.util.AbstractList;

import com.sun.jna.Pointer;

import core.Array;
import core.GTerror;
import core.GTerrorJava;
import core.Range;
import core.Str;
import extended.FeatureNode;
import gtnative.GT;
import gtnative.GT.TRACKSELECTOR;

public class Diagram {
  protected Pointer diagram_ptr;
  // these references are kept to avoid garbage collection
  // of the track selector functions as long as this object exists
  @SuppressWarnings("unused")
  private TRACKSELECTOR tsf;
  @SuppressWarnings("unused")
  private TrackSelector ts;

  public Diagram(AbstractList<FeatureNode> feats, Range rng, Style sty)
      throws GTerrorJava {
    Pointer dia;
    if (rng.get_start() > rng.get_end()) {
      throw new GTerrorJava("range.start > range.end");
    }
    Array gtarr = new Array(Pointer.SIZE);
    for (int i = 0; i < feats.size(); i++) {
      gtarr.add(feats.get(i).to_ptr());
    }
    dia = GT.INSTANCE.gt_diagram_new_from_array(gtarr.to_ptr(), rng, sty
        .to_ptr());
    if (dia == null) {
      throw new GTerrorJava("Diagram pointer was NULL");
    } else {
      this.diagram_ptr = dia;
    }
  }

  public Diagram(FeatureIndex feat_index, String seqid, Range ran, Style style)
      throws GTerrorJava {
    Pointer dia;
    if (ran.start.longValue() > ran.end.longValue()) {
      throw new GTerrorJava("range.start > range.end");
    }
    if (style.equals(Style.class)) {
      throw new GTerrorJava("style parameter has to be a style Object");
    }
    GTerror err = new GTerror();
    synchronized (this) {
      dia = GT.INSTANCE.gt_diagram_new(feat_index.to_ptr(), seqid, ran, style
          .to_ptr(), err.to_ptr());
    }
    if (dia == null) {
      throw new GTerrorJava(err.get_err());
    } else {
      this.diagram_ptr = dia;
    }
  }

  public void set_track_selector_func(final TrackSelector ts) {
    TRACKSELECTOR tsf = new TRACKSELECTOR() {
      public void callback(Pointer block_ptr, Pointer str_ptr, Pointer data_ptr) {
        Block b = new Block(block_ptr);
        String s;
        try {
          s = ts.getTrackId(b);
        } catch (Exception e) {
          s = "<not a string or error: " + e.toString() + ">";
        }
        Str str = new Str(str_ptr);
        str.append_str(s);
      }
    };
    this.tsf = tsf;
    this.ts = ts;
    GT.INSTANCE.gt_diagram_set_track_selector_func(diagram_ptr, tsf);
  }

  public void reset_track_selector_func() {
    GT.INSTANCE.gt_diagram_reset_track_selector_func(diagram_ptr);
  }

  protected void finalize() throws Throwable {
    try {
      GT.INSTANCE.gt_diagram_delete(diagram_ptr);
    } finally {
      super.finalize();
    }
  }

  public Pointer to_ptr() {
    return diagram_ptr;
  }
}
