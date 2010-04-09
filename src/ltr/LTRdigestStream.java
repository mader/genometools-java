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
    Pointer opts = GT.INSTANCE.gt_encodedsequence_options_new();
    GT.INSTANCE.gt_encodedsequence_options_enable_tis_table_usage(opts);
    GT.INSTANCE.gt_encodedsequence_options_enable_des_table_usage(opts);
    GT.INSTANCE.gt_encodedsequence_options_enable_ssp_table_usage(opts);
    GT.INSTANCE.gt_encodedsequence_options_enable_sds_table_usage(opts);
    GT.INSTANCE.gt_encodedsequence_options_set_logger(opts, l.to_ptr());
    GT.INSTANCE.gt_encodedsequence_options_set_indexname(opts, str_indexname.to_ptr());
    if (!((new File(indexname + ".esq")).exists())) {
      Str smapfile = new Str(""), sat = new Str("");
      StrArray filenametab = new StrArray();
      filenametab.add(indexname);
      GT.INSTANCE.gt_encodedsequence_options_set_access_type(opts, sat.to_ptr());
      GT.INSTANCE.gt_encodedsequence_options_set_symbolmap_file(opts, smapfile.to_ptr());
      GT.INSTANCE.gt_encodedsequence_options_set_input_sequences(opts, filenametab.to_ptr());
      GT.INSTANCE.gt_encodedsequence_options_set_input_dna(opts);
      encodedseq = GT.INSTANCE.gt_encodedsequence_new_from_files(opts, err.to_ptr());
      if (encodedseq == Pointer.NULL)
        throw new GTerrorJava(err.get_err());
    } else {
      encodedseq = GT.INSTANCE.gt_encodedsequence_new_from_index(1, opts, err.to_ptr());
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
