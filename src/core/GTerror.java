package core;

import com.sun.jna.*;
import gtnative.*;

// The GtError class is a Throwable that is able to communicate low level genometools API Errors to the user of the Api
// Therefore it has four Constructos which can be used to attach a cause to the GTerror Object
public class GTerror {
  private static final long serialVersionUID = 813149150202370867L;
  private boolean own_err;
  private Pointer err;

  public GTerror() {
    this.err = GT.INSTANCE.gt_error_new();
    own_err = true;
  }

  public GTerror(Pointer err_p) {
    this.err = err_p;
    own_err = false;
  }

  public void set(String msg) {
    GT.INSTANCE.gt_error_set(this.err, msg);
  }

  // public GTerror(String err_mess)
  // {
  // super(err_mess);
  // this.err = GT.INSTANCE.gt_error_new();
  // }
  //
  // public GTerror(String err_mess, Throwable cause)
  // {
  // super(err_mess, cause);
  // this.err = GT.INSTANCE.gt_error_new();
  // }
  //
  // public GTerror(String err_mess, Pointer err)
  // {
  // super(err_mess);
  // this.err = err;
  // }
  //
  // public GTerror(String err_mess, Throwable cause, Pointer err)
  // {
  // super(err_mess, cause);
  // this.err = err;
  // }
  protected void finalize() {
    if (own_err)
      GT.INSTANCE.gt_error_delete(this.err);
    this.err = null;
  }

  public String get_err() {
    return GT.INSTANCE.gt_error_get(this.err);
  }

  public Boolean is_set() {
    return (GT.INSTANCE.gt_error_is_set(this.err) != 0);
  }

  public void unset() {
    GT.INSTANCE.gt_error_unset(this.err);
  }

  // public static void gtexcept(GTerror err) throws GTerror
  // {
  // if (err instanceof GTerror) {
  // throw new GTerror("Genometools Error " + err.get() + "");
  // }
  // }
  // public static void gtexcept(String err) throws GTerror
  // {
  // throw new GTerror("Java Wrapper Exception: " + err + "");
  // }

  public Pointer to_ptr() {
    return err;
  }
}
