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

import java.io.File;
import gtnative.*;
import java.io.IOException;

import com.sun.jna.Pointer;
import core.StrArray;

public class GFF3InStream extends GenomeStream {
  public GFF3InStream(String filename) throws IOException {

    File fi = new File(filename);
    if (!fi.exists()) {
      throw new IOException(filename + " does not exist");
    } else {
      genome_stream = GT.INSTANCE.gt_gff3_in_stream_new_sorted(filename);
    }
    set_disposed(false);
  }

  public StrArray used_types() {
    Pointer str_array_ptr = GT.INSTANCE
        .gt_gff3_in_stream_get_used_types(this.genome_stream);
    StrArray str_arr = new StrArray(str_array_ptr);
    return str_arr;
  }
}
