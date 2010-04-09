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


package core;

import com.sun.jna.*;

import gtnative.*;

public class Logger {
  private Pointer logger_ptr;
  private boolean disposed;

  public Logger(Boolean enabled, String prefix) {
    Pointer stderr = NativeLibrary.getInstance ("c").getFunction("stdout").getPointer(0);
    logger_ptr = GT.INSTANCE.gt_logger_new(enabled ? 1 : 0, prefix, stderr);
    disposed = false;
  }

  public Logger(Pointer ptr) {
    logger_ptr = ptr;
    disposed = false;
  }

  public synchronized void dispose() {
    if (!disposed) {
      GT.INSTANCE.gt_logger_delete(this.logger_ptr);
      disposed = true;
    }
  }
  
  protected void finalize() {
    if (!disposed) {
      dispose();
    }
  }

  public Pointer to_ptr() {
    return logger_ptr;
  }
}
