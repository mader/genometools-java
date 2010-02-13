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
import com.sun.jna.ptr.PointerByReference;

import core.GTerror;
import core.GTerrorJava;
import core.Str;
import extended.GenomeStream;
import gtnative.GTunstable;

public class LTRdigestStream extends GenomeStream {
  private Pointer encodedseq;
  private Pointer verboseinfo;
  private Str indexname_str;
  private int tests_to_run = 0;

  public LTRdigestStream(GenomeStream instream, String indexname,
      PBSOptions pbs_opts, PPTOptions ppt_opts, PdomOptions pdom_opts)
      throws GTerrorJava, IOException {
    
    File f = new File(indexname+".prj");
    if (!f.exists()) {
      throw new IOException("Invalid indexname: " + indexname);
    }
    
    GTerror err = new GTerror();
    indexname_str = new Str(indexname);

    if (pdom_opts != null) {
      tests_to_run |= 4;
    }
    if (pbs_opts != null) {
      tests_to_run |= 2;
    }
    if (ppt_opts != null) {
      tests_to_run |= 1;
    }

    verboseinfo = GTunstable.INSTANCE.newverboseinfo(0);
    if (verboseinfo == Pointer.NULL) {
      throw new GTerrorJava("could not initialize verboseinfo");
    }
    encodedseq = GTunstable.INSTANCE.mapencodedsequence(1, // withrange
        indexname_str.to_ptr(), // indexname
        1, // withesqtab
        1, // withdestab
        1, // withsdstab
        1, // withssptab
        verboseinfo, err.to_ptr());
    if (encodedseq == Pointer.NULL) {
      throw new GTerrorJava(err.get_err());
    }

    genome_stream = GTunstable.INSTANCE.gt_ltrdigest_stream_new(instream
        .to_ptr(), tests_to_run, encodedseq, pbs_opts, ppt_opts, pdom_opts, err
        .to_ptr());
    if (genome_stream == Pointer.NULL) {
      PointerByReference vbi_pp = new PointerByReference(this.verboseinfo);
      PointerByReference encseq_pp = new PointerByReference(this.encodedseq);
      GTunstable.INSTANCE.freeverboseinfo(vbi_pp);
      GTunstable.INSTANCE.encodedsequence_free(encseq_pp);
      throw new GTerrorJava(err.get_err());
    }
  }

  public synchronized void finalize() {
    PointerByReference vbi_pp = new PointerByReference(this.verboseinfo);
    PointerByReference encseq_pp = new PointerByReference(this.encodedseq);
    GTunstable.INSTANCE.freeverboseinfo(vbi_pp);
    GTunstable.INSTANCE.encodedsequence_free(encseq_pp);
  }
}
