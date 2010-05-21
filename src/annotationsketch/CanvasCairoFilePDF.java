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

package annotationsketch;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

import gtnative.GT;
import core.GTerrorJava;

public class CanvasCairoFilePDF extends CanvasCairoBase {
  public CanvasCairoFilePDF(Style style, int width, int height,
      ImageInfo image_info) throws GTerrorJava {
    NativeLong n_width = new NativeLong(width);
    NativeLong n_height = new NativeLong(height);
    Pointer ii_ptr;

    if (image_info == null) {
      ii_ptr = Pointer.NULL;
    } else {
      ii_ptr = image_info.to_ptr();
    }
    canvas_ptr = GT.INSTANCE.gt_canvas_cairo_file_new(style.to_ptr(),
        GraphicsOutType.GT_GRAPHICS_PDF.toInt(), n_width, n_height, ii_ptr);
    set_disposed(false);
  }

  public CanvasCairoFilePDF(Style style, int width, int height) {
    NativeLong n_width = new NativeLong(width);
    NativeLong n_height = new NativeLong(height);
    canvas_ptr = GT.INSTANCE.gt_canvas_cairo_file_new(style.to_ptr(),
        GraphicsOutType.GT_GRAPHICS_PDF.toInt(), n_width, n_height,
        Pointer.NULL);
    set_disposed(false);
  }
}
