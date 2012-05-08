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


package core;

import com.sun.jna.*;
import gtnative.*;

public class Range extends Structure {
  public static class ByReference extends Range implements Structure.ByReference { }

  public NativeLong start;
  public NativeLong end;

  public Range() {
    this.start = new NativeLong(0);
    this.end = new NativeLong(0);
  }

  public Range(int start, int end) {
    this.start = new NativeLong(start);
    this.end = new NativeLong(end);
  }

  public long length() {
    return GT.INSTANCE.gt_range_length(this).longValue();
  }

  public void set_start(int start) {
    NativeLong tmp = new NativeLong(start);
    this.start = tmp;
  }

  public void set_end(int end) {
    NativeLong tmp = new NativeLong(end);
    this.end = tmp;
  }

  public int get_start() {
    long l_tmp = this.start.longValue();
    return (int) l_tmp;
  }

  public int get_end() {
    long l_tmp = this.end.longValue();
    return (int) l_tmp;
  }
}
