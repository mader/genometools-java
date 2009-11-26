package annotationsketch;

import gtnative.GT;
import com.sun.jna.Pointer;

public abstract class Canvas
{
  protected Pointer canvas_ptr; 
 
  public Pointer to_ptr() {
    return canvas_ptr;
  }
  protected synchronized void finalize() {
	  GT.INSTANCE.gt_canvas_delete(canvas_ptr);
  }
}