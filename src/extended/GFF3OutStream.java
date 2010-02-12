package extended;

import com.sun.jna.Pointer;
import gtnative.*;

public class GFF3OutStream extends GenomeStream {

  public GFF3OutStream(GenomeStream in_stream) {
    genome_stream = GT.INSTANCE.gt_gff3_out_stream_new(in_stream.to_ptr(),
        Pointer.NULL);
  }
}
