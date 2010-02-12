/*
  Copyright (c) 2009 Philipp Carpus  <random234@gmx.net>
  Copyright (c) 2009 Center for Bioinformatics, University of Hamburg

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

import gtnative.GT;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

public class ImageInfo {
  private Pointer image_info_ptr;

  public ImageInfo() {
    image_info_ptr = GT.INSTANCE.gt_image_info_new();
  }

  protected void finalize() {
    GT.INSTANCE.gt_image_info_delete(image_info_ptr);
  }

  public int get_height() {
    NativeLong tmp = GT.INSTANCE.gt_image_info_get_height(image_info_ptr);
    int i_tmp = (int) tmp.longValue();
    return i_tmp;
  }

  public int num_of_rec_maps() {
    NativeLong tmp = GT.INSTANCE.gt_image_info_num_of_rec_maps(image_info_ptr);
    int i_tmp = (int) tmp.longValue();
    return i_tmp;
  }

  public RecMap get_rec_map(int i) {
    NativeLong tmp = new NativeLong(i);
    RecMap rm = new RecMap(GT.INSTANCE.gt_image_info_get_rec_map(
        image_info_ptr, tmp));
    return rm;
  }

  public Pointer to_ptr() {
    return image_info_ptr;
  }

}
