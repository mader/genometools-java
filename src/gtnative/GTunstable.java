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

package gtnative;

import ltr.PBSOptions;
import ltr.PPTOptions;
import ltr.PdomOptions;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public interface GTunstable extends Library {
  public static final GTunstable INSTANCE = new GTunstableMapping();

  /*------------------------------GtLTRdigestStream------------------------------*/
  Pointer gt_ltrdigest_stream_new(Pointer instream, int tests_to_run,
      Pointer encseq, PBSOptions pbs_opts, PPTOptions ppt_opts,
      PdomOptions pdom_opts, Pointer err_p);

  /*------------------------------Encodedsequence------------------------------*/
  Pointer newverboseinfo(int verbose);

  Pointer mapencodedsequence(int withrange, Pointer indexname, int withesqtab,
      int withdestab, int withsdstab, int withssptab, Pointer verboseinfo,
      Pointer err_p);

  void encodedsequence_free(PointerByReference encseqptr);

  void freeverboseinfo(PointerByReference verboseinfoptr);
}
