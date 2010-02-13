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

import java.math.BigDecimal;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

import core.GTerrorJava;
import core.Range;

public class PPTOptions extends Structure {
  public Range ppt_len, ubox_len;
  public double ppt_pyrimidine_prob, ppt_purine_prob, bkg_a_prob, bkg_g_prob,
      bkg_t_prob, bkg_c_prob, ubox_u_prob;
  public int radius;

  public PPTOptions() {
    this.ppt_len.start = new NativeLong(8);
    this.ppt_len.end = new NativeLong(30);
    this.ubox_len.start = new NativeLong(3);
    this.ubox_len.end = new NativeLong(30);
    this.ppt_purine_prob = 0.97;
    this.ppt_pyrimidine_prob = 0.03;
    this.bkg_a_prob = 0.25;
    this.bkg_c_prob = 0.25;
    this.bkg_g_prob = 0.25;
    this.bkg_t_prob = 0.25;
    this.ubox_u_prob = 0.91;
    this.radius = 60;
  }

  public void set_ppt_length(long min, long max) throws GTerrorJava {
    if (min > max) {
      throw new GTerrorJava("min (" + min + ") > max (" + max + ")");
    }
    this.ppt_len.start = new NativeLong(min);
    this.ppt_len.end = new NativeLong(max);
  }

  public void set_ubox_length(long min, long max) throws GTerrorJava {
    if (min > max) {
      throw new GTerrorJava("min (" + min + ") > max (" + max + ")");
    }
    this.ubox_len.start = new NativeLong(min);
    this.ubox_len.end = new NativeLong(max);
  }

  public void set_ppt_probabilities(double r_prob, double y_prob)
      throws GTerrorJava {
    if (BigDecimal.valueOf(r_prob).add(BigDecimal.valueOf(y_prob)).compareTo(
        BigDecimal.valueOf(1)) != 0) {
      throw new GTerrorJava("Probabilities must add up to 1");
    }
    this.ppt_purine_prob = r_prob;
    this.ppt_pyrimidine_prob = y_prob;
  }

  public void set_bkg_probabilities(double a, double c, double g, double t)
      throws GTerrorJava {
    if (BigDecimal.valueOf(a).add(
        BigDecimal.valueOf(c).add(BigDecimal.valueOf(g)).add(
            BigDecimal.valueOf(t))).compareTo(BigDecimal.valueOf(1)) != 0) {
      throw new GTerrorJava("Probabilities must add up to 1");
    }
    this.bkg_a_prob = a;
    this.bkg_c_prob = c;
    this.bkg_g_prob = g;
    this.bkg_t_prob = t;
  }
}
