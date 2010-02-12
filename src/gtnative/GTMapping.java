package gtnative;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import core.Range;

public class GTMapping implements GT {
  static {
    Native.register("genometools");
  }

  public native int gt_genome_node_accept(Pointer gn, Pointer gv, Pointer err);

  public native Pointer gt_genome_node_ref(Pointer gn);

  public native NativeLong gt_genome_node_get_start(Pointer gn);

  public native NativeLong gt_genome_node_get_end(Pointer gn);

  public native String gt_genome_node_get_filename(Pointer gn);
  
  public native Pointer gt_genome_node_get_seqid(Pointer gn);

  public native void gt_genome_node_delete(Pointer gn);

  public native void gt_genome_node_change_seqid(Pointer genome_node,
      Pointer GtStr);

  public native Pointer gt_feature_node_new(Pointer seqid, String type,
      NativeLong start, NativeLong end, int strand);

  public native void gt_feature_node_add_child(Pointer parent, Pointer child);

  public native String gt_feature_node_get_source(Pointer feature_node);

  public native void gt_feature_node_set_source(Pointer feature_node,
      Pointer source);

  public native String gt_feature_node_get_type(Pointer feature_node);

  public native int gt_feature_node_has_type(Pointer feature_node, String type);

  public native int gt_feature_node_score_is_defined(Pointer feature_node);

  public native float gt_feature_node_get_score(Pointer feature_node);

  public native void gt_feature_node_set_score(Pointer feature_node, float score);

  public native void gt_feature_node_unset_score(Pointer feature_node);

  public native int gt_feature_node_get_strand(Pointer feature_node);

  public native void gt_feature_node_set_strand(Pointer feature_node, int strand);

  public native int gt_feature_node_get_phase(Pointer feature_node);

  public native void gt_feature_node_set_phase(Pointer feature_node, int strand);

  public native String gt_feature_node_get_attribute(Pointer feature_node,
      String name);

  public native void gt_feature_node_add_attribute(Pointer feature_node,
      String tag, String value);

  public native Pointer gt_feature_node_get_attribute_list(Pointer feature_node);

  public native void gt_genome_node_mark(Pointer genome_node);

  public native int gt_genome_node_is_marked(Pointer genome_node);

  public native Pointer gt_layout_new(Pointer diagram, NativeLong width,
      Pointer gt_style, Pointer err);

  public native NativeLong gt_layout_get_height(Pointer gt_lay_ptr);

  public native int gt_layout_sketch(Pointer gt_lay_ptr, Pointer target_canvas,
      Pointer err);

  public native void gt_layout_delete(Pointer gt_lay_ptr);

  public native Pointer gt_diagram_new(Pointer feat_index, String seqid,
      Range gt_range, Pointer gt_style, Pointer gt_err);

  public native Pointer gt_diagram_new_from_array(Pointer gt_array,
      Range gt_range, Pointer gt_style);

  public native void gt_diagram_set_track_selector_func(Pointer gt_diagram,
      GT.TRACKSELECTOR func);

  public native void gt_diagram_reset_track_selector_func(Pointer diagram_ptr);

  public native void gt_diagram_delete(Pointer gt_diagram);

  public native Pointer gt_block_ref(Pointer gt_block);

  public native Range gt_block_get_range_ptr(Pointer gt_block);

  public native String gt_block_get_type(Pointer gt_block);

  public native int gt_block_has_only_one_fullsize_element(Pointer gt_block);

  public native void gt_block_merge(Pointer gt_block, Pointer gt_block_sec);

  public native Pointer gt_block_clone(Pointer gt_block);

  public native void gt_block_set_strand(Pointer gt_block, int i);

  public native Pointer gt_block_get_top_level_feature(Pointer gt_block);

  public native int gt_block_get_strand(Pointer gt_block);

  public native NativeLong gt_block_get_size(Pointer gt_block);

  public native void gt_block_delete(Pointer gt_block);

  public native Pointer gt_canvas_cairo_file_new(Pointer style,
      int output_type, NativeLong width, NativeLong height, Pointer image_info);

  public native int gt_canvas_cairo_file_to_file(Pointer canvas,
      String filename, Pointer err);

  public native void gt_canvas_delete(Pointer canvas);

  public native Pointer gt_feature_index_memory_new();

  public native Pointer gt_feature_index_memory_get_node_by_ptr(Pointer fim,
      NativeLong id, Pointer err);

  public native void gt_feature_index_delete(Pointer fi);

  public native void gt_feature_index_add_feature_node(Pointer feature_index,
      Pointer feature_node);

  public native int gt_feature_index_add_gff3file(Pointer fi, String gff3file,
      Pointer err);

  public native Pointer gt_feature_index_get_features_for_seqid(Pointer fi,
      String seqid);

  public native String gt_feature_index_get_first_seqid(Pointer fi);

  public native Pointer gt_feature_index_get_seqids(Pointer fi);

  public native void gt_feature_index_get_range_for_seqid(Pointer fi,
      Range rng, String seqid);

  public native int gt_feature_index_has_seqid(Pointer fi, String seqid);

  /*------------------------------GtImageInfo------------------------------*/
  public native Pointer gt_image_info_new();

  public native NativeLong gt_image_info_get_height(Pointer ii_ptr);

  public native NativeLong gt_image_info_num_of_rec_maps(Pointer ii_ptr);

  public native Pointer gt_image_info_get_rec_map(Pointer ii_ptr, NativeLong i);

  public native void gt_image_info_delete(Pointer ii_ptr);

  /*------------------------------GtRecMap------------------------------*/
  public native double gt_rec_map_get_northwest_x(Pointer rec_map);

  public native double gt_rec_map_get_northwest_y(Pointer rec_map);

  public native double gt_rec_map_get_southeast_x(Pointer rec_map);

  public native double gt_rec_map_get_southeast_y(Pointer rec_map);

  public native Pointer gt_rec_map_get_genome_feature(Pointer rec_map);

  public native Pointer gt_rec_map_ref(Pointer rec_map);

  public native void gt_rec_map_delete(Pointer rec_map);

  public native int gt_rec_map_has_omitted_children(Pointer rec_map);

  /*------------------------------GtStyle------------------------------*/
  public native Pointer gt_style_new(Pointer err);

  public native int gt_style_load_file(Pointer style, String str, Pointer err);

  public native int gt_style_load_str(Pointer style, Pointer str, Pointer err);

  public native int gt_style_to_str(Pointer style, Pointer str, Pointer err);

  // feature_node Object should be set to null as long as it isn't used
  public native int gt_style_get_color(Pointer style, String sect, String key,
      Pointer color, Pointer feat_node);

  public native void gt_style_set_color(Pointer style, String sect, String key,
      Pointer color);

  // feature_node Object should be set to null as long as it isn't used
  public native int gt_style_get_str(Pointer style, String sect, String key,
      Pointer str, Pointer feat_node);

  public native void gt_style_set_str(Pointer style, String sect, String key,
      Pointer str);

  // feature_node Object should be set to null as long as it isn't used
  public native int gt_style_get_num(Pointer style, String sect, String key,
      DoubleByReference i, Pointer feat_node);

  public native void gt_style_set_num(Pointer style, String sect, String key,
      DoubleByReference i);

  // feature_node Object should be set to null as long as it isn't used
  public native int gt_style_get_bool(Pointer style, String sect, String key,
      IntByReference b, Pointer feat_node);

  public native void gt_style_set_bool(Pointer style, String sect, String key,
      int b);

  public native void gt_style_unset(Pointer style, String sect, String key);

  public native void gt_style_delete(Pointer style);

  /*------------------------------GtArray------------------------------*/
  public native Pointer gt_array_new(NativeLong size_of_elem);

  public native Pointer gt_array_get(Pointer array, NativeLong index);

  public native void gt_array_add_ptr(Pointer array, Pointer elem);

  public native NativeLong gt_array_size(Pointer array);

  public native void gt_array_delete(Pointer array);

  public native Pointer gt_array_ref(Pointer array);

  /*------------------------------GtError------------------------------*/
  public native Pointer gt_error_new();

  public native void gt_error_set(Pointer err, String msg);

  public native String gt_error_get(Pointer err);

  public native int gt_error_is_set(Pointer err);

  public native void gt_error_unset(Pointer err);

  public native void gt_error_delete(Pointer err);

  /*------------------------------GtStr------------------------------*/
  public native Pointer gt_str_new();

  public native Pointer gt_str_new_cstr(String cstr);

  public native Pointer gt_str_ref(Pointer s);

  public native void gt_str_append_str(Pointer dest, Pointer src);

  public native void gt_str_append_cstr(Pointer dest, String str);

  public native String gt_str_get(Pointer s);

  public native NativeLong gt_str_length(Pointer str);

  public native void gt_str_delete(Pointer str);

  /*------------------------------GtStrArray------------------------------*/
  public native Pointer gt_str_array_new();

  public native void gt_str_array_add_cstr(Pointer str_array, String cstr);

  public native String gt_str_array_get(Pointer str_array, NativeLong strnum);

  public native NativeLong gt_str_array_size(Pointer str_array);

  public native void gt_str_array_delete(Pointer str_array);

  /*------------------------------Allocators------------------------------*/
  private static native void gt_lib_init();

  @SuppressWarnings("unused")
  private static native void gt_lib_reg_atexit_func();
  
  private static native void gt_lib_clean();

  /*------------------------------GtRange------------------------------*/
  public native NativeLong gt_range_length(Range rng);

  /*------------------------------GtFeatureNodeIterator------------------------------*/
  public native Pointer gt_feature_node_iterator_next(Pointer gn);

  public native void gt_feature_node_iterator_delete(Pointer gn);

  public native Pointer gt_feature_node_iterator_new(Pointer gn);

  public native Pointer gt_feature_node_iterator_new_direct(Pointer gn);

  /*------------------------------GtGenomeStream------------------------------*/
  public native int gt_node_stream_next(Pointer node_stream,
      PointerByReference genome_node, Pointer err);

  /*------------------------------GtGFF3InStream------------------------------*/
  public native Pointer gt_gff3_in_stream_new_sorted(String filename);

  public native Pointer gt_gff3_in_stream_get_used_types(Pointer node_stream);

  /*------------------------------GtScriptWrapperStream------------------------------*/
  public native Pointer gt_script_wrapper_stream_new(GT.STREAMNEXTFUNC func,
      Pointer ptr);

  /*------------------------------GtGFF3OutStream------------------------------*/
  public native Pointer gt_gff3_out_stream_new(Pointer in, Pointer ptr);
  
  /*------------------------------GtNodeVisitor------------------------------*/
  public native void gt_node_visitor_delete(Pointer visitor);

  /*------------------------------GtScriptWrapperVisitor------------------------------*/
  public native Pointer gt_script_wrapper_visitor_new(GT.VISITORFUNC comment_node_visit_func,
                                                      GT.VISITORFUNC feature_node_visit_func,
                                                      GT.VISITORFUNC region_node_visit_func,
                                                      GT.VISITORFUNC sequence_node_visit_func,
                                                      Pointer ffunc);
  
  public GTMapping() {
    /* initialize globals in this library instance */
    synchronized (this) {
      gt_lib_init();
    }
  }
  
  protected synchronized void finalize() {
    /* finalize globals in this library instance */
    gt_lib_clean();
  }

}
