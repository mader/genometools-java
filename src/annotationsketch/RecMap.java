package annotationsketch;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class RecMap 
{
  protected Pointer rec_map;
  public interface GT extends Library
  {
    GT INSTANCE = (GT) Native.loadLibrary("genometools", GT.class);
    double gt_rec_map_get_northwest_x(Pointer rec_map);
    double gt_rec_map_get_northwest_y(Pointer rec_map);
    double gt_rec_map_get_southeast_x(Pointer rec_map);
    double gt_rec_map_get_southeast_y(Pointer rec_map);
    Pointer gt_rec_map_get_genome_feature(Pointer rec_map);
    boolean gt_rec_map_has_omitted_children(Pointer rec_map);
  }
  
  public RecMap(Pointer rm){
    this.rec_map = rm;
  }
  
  public double get_northwest_x() {
   return GT.INSTANCE.gt_rec_map_get_northwest_x(rec_map);
  }
  
  public double get_northwest_y() {
    return GT.INSTANCE.gt_rec_map_get_northwest_y(rec_map);
   }
  
  public double get_southeast_x() {
    return GT.INSTANCE.gt_rec_map_get_southeast_x(rec_map);
  }
  public double get_southeast_y() {
    return GT.INSTANCE.gt_rec_map_get_southeast_y(rec_map);
  }
  
  public Pointer get_genome_feature() {
    return GT.INSTANCE.gt_rec_map_get_genome_feature(rec_map);
  }
   
  public boolean has_omitted_children() {
    return GT.INSTANCE.gt_rec_map_has_omitted_children(rec_map);
  }
}



