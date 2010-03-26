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

package ltr;

import java.io.File;
import java.io.IOException;

import com.sun.jna.Pointer;

import core.GTerror;
import core.GTerrorJava;
import core.Logger;
import core.Str;
import core.StrArray;
import extended.GenomeStream;
import gtnative.GT;

public class LTRdigestStream extends GenomeStream {
  private Pointer encodedseq;
  private int tests_to_run = 0;
  private boolean disposed_extensions = true;

  public LTRdigestStream(GenomeStream instream, String indexname,
      PBSOptions pbs_opts, PPTOptions ppt_opts, PdomOptions pdom_opts)
      throws GTerrorJava, IOException {

    GTerror err = new GTerror();
    Logger l = new Logger(false, "# ");

    Str str_indexname = new Str(indexname);
    if (!((new File(indexname + ".prj")).exists())) {
      Str smapfile = new Str(""), satfile = new Str("");
      StrArray filenametab = new StrArray();
      filenametab.add(indexname);
      encodedseq = GT.INSTANCE.gt_encodedsequence_new_from_files(null,
          str_indexname.to_ptr(), smapfile.to_ptr(), satfile.to_ptr(),
          filenametab.to_ptr(), 1, /* isdna */
          0, /* isprotein */
          0, /* isplain */
          1, /* withtistab */
          1, /* withdestab */
          1, /* withsdstab */
          1, /* withssptab */
          l.to_ptr(), err.to_ptr());
      if (encodedseq == Pointer.NULL)
        throw new GTerrorJava(err.get_err());
    } else {
      encodedseq = GT.INSTANCE.gt_encodedsequence_new_from_index(1,
          str_indexname.to_ptr(), 1, /* withtistab */
          1, /* withdestab */
          1, /* withsdstab */
          1, /* withssptab */
          l.to_ptr(), err.to_ptr());
      if (encodedseq == Pointer.NULL)
        throw new GTerrorJava(err.get_err());
    }

    if (pdom_opts != null) {
      tests_to_run |= 4;
    }
    if (pbs_opts != null) {
      tests_to_run |= 2;
    }
    if (ppt_opts != null) {
      tests_to_run |= 1;
    }

    genome_stream = GT.INSTANCE.gt_ltrdigest_stream_new(instream.to_ptr(),
        tests_to_run, encodedseq, pbs_opts, ppt_opts, pdom_opts, err.to_ptr());
    if (genome_stream == Pointer.NULL) {
      GT.INSTANCE.gt_encodedsequence_delete(encodedseq);
      disposed_extensions = false;
      throw new GTerrorJava(err.get_err());
    }
  }

  public synchronized void dispose() {
    if (!disposed_extensions) {
      GT.INSTANCE.gt_encodedsequence_delete(encodedseq);
      disposed_extensions = true;
    }
    super.dispose();
  }

  public void finalize() {
    dispose();
  }
}
