package extended;

import gtnative.*;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import core.GTerrorJava;
import core.GTerror;

public class GenomeStream {
    Pointer genome_stream = null;

    public FeatureNode next_tree() throws GTerrorJava {
	GTerror err = new GTerror();
	PointerByReference genome_node = new PointerByReference();
	int rval;
	rval = GT.INSTANCE.gt_node_stream_next(this.genome_stream, genome_node,
		err.to_ptr());
	if (rval != 0) {
	    throw new GTerrorJava(err.get_err());
	}
	if (genome_node.getValue() == Pointer.NULL) {
	    System.out.println("GenomeStream Debug NULL");
	    return null;
	} else {
	    System.out.println("GenomeStream Debug FEAT_NODE");
	    return new FeatureNode(genome_node.getValue());
	}
    }
}
