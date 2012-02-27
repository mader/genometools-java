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

import com.sun.jna.Structure;

public class Color extends Structure{
  public double red;
  public double green;
  public double blue;
  public double alpha;
  
  public Color(){
  }
  
  public Color(double red, double green, double blue, double alpha){
	 this.red =  red;
	 this.green = green;
	 this.blue = blue;
	 this.alpha = alpha;
	 this.write();
  }

  public double getRed() {
	return red;
  }

  public void setRed(double red) {
	this.red = red;
  }

  public double getGreen() {
	return green;
  }

  public void setGreen(double green) {
	this.green = green;
  }

  public double getBlue() {
	return blue;
  }

  public void setBlue(double blue) {
	this.blue = blue;
  }

  public double getAlpha() {
	return alpha;
  }

  public void setAlpha(double alpha) {
	this.alpha = alpha;
  }
}