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
	return new FeatureNode(GT.INSTANCE
		.gt_rec_map_get_genome_feature(rec_map));
    }

    public boolean has_omitted_children() {
	return (GT.INSTANCE.gt_rec_map_has_omitted_children(rec_map) == 1);
    }
}
