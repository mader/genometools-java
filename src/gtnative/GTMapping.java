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

package gtnative;

import ltr.PBSOptions;
import ltr.PPTOptions;
import ltr.PdomOptions;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.StringArray;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;
import com.sun.jna.ptr.PointerByReference;

import core.Range;

public class GTMapping implements GT {
  static {
    Native.register("genometools");
  }

  /*------------------------------RDB------------------------------*/
  
  public native void gt_rdb_delete(Pointer db);
  
  public native Pointer gt_rdb_mysql_new(String server, int port,
          String database, String username,
          String password, Pointer err);
  
  /*------------------------------other stuff------------------------------*/
  
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

  public native void gt_feature_node_mark(Pointer genome_node);

  public native int gt_feature_node_is_marked(Pointer genome_node);

  public native Pointer gt_layout_new(Pointer diagram, NativeLong width,
      Pointer gt_style, Pointer err);

  public native int gt_layout_get_height(Pointer gt_lay_ptr,
      NativeLongByReference result, Pointer err);

  public native void gt_layout_set_track_ordering_func(Pointer layout_ptr,
      GT.TRACKORDERINGFUNC func);

  public native void gt_layout_unset_track_ordering_func(Pointer layout_ptr);

  public native int gt_layout_sketch(Pointer gt_lay_ptr, Pointer target_canvas,
      Pointer err);

  public native void gt_layout_delete(Pointer gt_lay_ptr);

  public native Pointer gt_diagram_new(Pointer feat_index, String seqid,
      Range gt_range, Pointer gt_style, Pointer gt_err);

  public native Pointer gt_diagram_new_from_array(Pointer gt_array,
      Range gt_range, Pointer gt_style);

  public native Pointer gt_diagram_new_from_feature_collection(Pointer features,
          Range range,
          Pointer style);
  
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
      int output_type, NativeLong width, NativeLong height, Pointer image_info,
      Pointer err);

  public native int gt_canvas_cairo_file_to_file(Pointer canvas,
      String filename, Pointer err);

  public native void gt_canvas_delete(Pointer canvas);

  public native Pointer gt_feature_index_memory_new();

  public native Pointer gt_feature_index_memory_get_node_by_ptr(Pointer fim,
      NativeLong id, Pointer err);

  public native void gt_feature_index_delete(Pointer fi);

  public native int gt_feature_index_add_feature_node(Pointer feature_index,
      Pointer feature_node, Pointer err);

  public native int gt_feature_index_remove_node(Pointer feature_index,
      Pointer feature_node, Pointer err);

  public native int gt_feature_index_add_gff3file(Pointer fi, String gff3file,
      Pointer err);

  public native Pointer gt_feature_index_get_features_for_seqid(Pointer fi,
      String seqid, Pointer err);

  public native String gt_feature_index_get_first_seqid(Pointer fi,
      Pointer err);

  public native Pointer gt_feature_index_get_seqids(Pointer fi, Pointer err);

  public native int gt_feature_index_get_range_for_seqid(Pointer fi,
      Range.ByReference rng, String seqid, Pointer err);

  public native int gt_feature_index_has_seqid(Pointer fi, Pointer has_seqid,
      String seqid, Pointer err);

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

  public native void gt_error_set_nonvariadic(Pointer err, String msg);

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

  public native int gt_str_cmp(Pointer str1_ptr, Pointer str2_ptr);

  public native void gt_str_delete(Pointer str);

  /*------------------------------GtStrArray------------------------------*/
  public native Pointer gt_str_array_new();

  public native void gt_str_array_add_cstr(Pointer str_array, String cstr);

  public native String gt_str_array_get(Pointer str_array, NativeLong strnum);

  public native NativeLong gt_str_array_size(Pointer str_array);

  public native void gt_str_array_delete(Pointer str_array);

/*------------------------------GtFeatureCollection------------------------------*/
  
  public native Pointer gt_feature_collection_new();
  
  public native Pointer gt_feature_collection_new_from_array(Pointer array);
  
  public native void gt_feature_collection_add(Pointer fc, Pointer gn);
  
  public native void gt_feature_collection_add_array(Pointer fc, Pointer array);
  
  public native Pointer gt_feature_collection_get(Pointer fc, int index);
  
  public native NativeLong gt_feature_collection_size(Pointer fc);
  
  public native Pointer gt_feature_collection_to_array(Pointer fc);
  
  public native void gt_feature_collection_delete_contents(Pointer fc);
  
  public native void gt_feature_collection_delete(Pointer fc);
  
 /*------------------------------GtAnnoDB------------------------------*/
  
  public native void gt_anno_db_schema_delete(Pointer schema);
  
  public native Pointer gt_anno_db_schema_get_feature_index(Pointer schema,
          Pointer db, Pointer err);
  
  /*------------------------------GtAnnoDBEnsembl------------------------------*/
  
  public native Pointer gt_anno_db_ensembl_new();
  
  public native int gt_anno_db_feature_index_get_version(Pointer gfi, Pointer err);
  
  public native Pointer gt_ensembl_gene_adaptor_new(int ensembl_version);
  
  public native int gt_ensembl_fetch_gene_for_symbol(Pointer ga,
		  								Pointer gfi,
		  								PointerByReference gn,
		  								String gene_name,
		  								Pointer err);
  
  public native int gt_ensembl_fetch_gene_for_stable_id(Pointer ga,
		  									Pointer gfi,
		  									PointerByReference gn,
		  									String stable_id,
		  									Pointer err);
  
  public native int gt_ensembl_fetch_genes_for_range(Pointer ga,
		  								Pointer gfi,
		  								Pointer results,
		  								String seqid,
		  								Range qry_range,
		  								StringArray biotype_filter,
		  								int b_length,
		  								Pointer err);
  
  public native void gt_ensembl_gene_adaptor_delete(Pointer ga);
  
  public native Pointer gt_ensembl_karyo_adaptor_new(int ensembl_version);
  
  public native int gt_ensembl_fetch_range_for_karyoband(Pointer ka,
		  									Pointer gfi,
		  									Range range,
		  									String chr,
		  									String band,
		  									Pointer err);
  
  public native int gt_ensembl_fetch_karyobands_for_range(Pointer ka,
		  										Pointer gfi,
		  										Pointer results,
		  										String seqid,
		  										Range qry_range,
		  										Pointer err);
  
  public native void gt_ensembl_karyo_adaptor_delete(Pointer ka);
  
  /*------------------------------GtAnnoDBFO------------------------------*/
  
  public native Pointer gt_anno_db_fo_new();
  
  public native void  gt_feature_index_fo_filter_segment_only(Pointer fi);
  public native void  gt_feature_index_fo_filter_mutations_only(Pointer fi);
  public native void  gt_feature_index_fo_filter_translocations_only(Pointer fi);
  public native void  gt_feature_index_fo_filter_generic_only(Pointer fi);
  public native void  gt_feature_index_fo_reset_filter_type(Pointer fi);

  public native void  gt_feature_index_fo_unset_all_filters(Pointer fi);

  public native void gt_feature_index_fo_set_segments_lower_th(Pointer fi,
                                                 double lower_th);
  public native  void gt_feature_index_fo_unset_segments_lower_th(Pointer fi);

  public native void gt_feature_index_fo_set_segments_upper_th(Pointer fi,
                                                 double upper_th);
  public native void gt_feature_index_fo_unset_segments_upper_th(Pointer fi);

  public native void gt_feature_index_fo_set_track_id(Pointer fi,
                                        String track_id);
  public native void gt_feature_index_fo_unset_track_id(Pointer fi);

  public native void gt_feature_index_fo_set_segments_sorted(Pointer fi,
                                               boolean sorted);

  public native void gt_feature_index_fo_set_score(Pointer fi,
                                     double score,
                                     boolean grater_than);

  public native void gt_feature_index_fo_unset_score(Pointer fi);

  public native void gt_feature_index_fo_add_where_clause_int_filter(Pointer fi,
                                                       String column,
                                                       int[] filter,
                                                       int length);
  public native void gt_feature_index_fo_add_where_clause_str_filter(Pointer fi,
                                                       String column,
                                                       StringArray filter,
                                                       int length);
  public native void gt_feature_index_fo_add_where_clause_double_filter(Pointer fi,
																			String column,
																			double[] filter,
																			int length);

  public native void gt_feature_index_fo_reset_where_clause_int_filter(Pointer fi);
  public native void gt_feature_index_fo_reset_where_clause_str_filter(Pointer fi);
  public native void gt_feature_index_fo_reset_where_clause_double_filter(Pointer fi);
  
  public native void gt_feature_index_fo_add_generic_filter(Pointer fi,
																StringArray filter,
																int length);
  public native void gt_feature_index_fo_add_project_filter(Pointer fi,
                                              int[] filter,
                                              int length);
  public native void gt_feature_index_fo_add_tissue_filter(Pointer fi,
                                             int[] filter,
                                             int length);

  public native void gt_feature_index_fo_set_additional_experiment_filter(Pointer fi,
                                                            int[] filter,
                                                            int length);
                                                            
  public native void gt_feature_index_fo_unset_additional_experiment_filter(
                                                          Pointer fi);

  public native void gt_feature_index_fo_add_somatic_filter(Pointer fi,
																StringArray filter,
																int length);
  public native void gt_feature_index_fo_add_confidence_filter(Pointer fi,
																	StringArray filter,
																	int length);
  public native void gt_feature_index_fo_add_snptool_filter(Pointer fi,
																StringArray filter,
																int length);
  
  public native void gt_feature_index_fo_set_location(Pointer fi, String seqid, Range range);
  
  public native int gt_feature_index_fo_get_features(Pointer fi,
                                      Pointer results,
                                      Pointer err);
  
  public native int gt_feature_index_fo_sort_segments_for_coverage(Pointer segments);
  
  public native int gt_feature_index_fo_process_mutations(Pointer results,
                                        Pointer mutations,
                                        Pointer rdb,
                                        String track_id,
                                        StringArray biotype_filter,
                                        int b_length,
                                        Pointer err);
  
  public native int gt_feature_index_fo_process_translocations(Pointer fi,
		  															Pointer translocations,
		  															Pointer rdb,
		  															String track_id,
		  															StringArray biotype_filter,
		  															int b_length,
		  															Pointer err);
  
  /*------------------------------GtLogger------------------------------*/
  public native Pointer gt_logger_new(int enabled, String prefix, Pointer target);

  public native void gt_logger_enable(Pointer logger);

  public native void gt_logger_disable(Pointer logger);

  public native int gt_logger_enabled(Pointer logger);

  public native void gt_logger_delete(Pointer logger);

  /*------------------------------GtEncseq------------------------------*/

  public native NativeLong gt_encseq_total_length(Pointer encseq);

  public native NativeLong gt_encseq_num_of_sequences(Pointer encseq);

  public native char gt_encseq_get_encoded_char(Pointer encseq, NativeLong pos,
      int readmode);

  public native char gt_encseq_get_decoded_char(Pointer encseq, NativeLong pos,
      int readmode);

  public native Pointer gt_encseq_ref(Pointer encseq);

  public native Pointer gt_encseq_create_reader_with_readmode(Pointer encseq,
      int readmode, NativeLong startpos);

  public native void gt_encseq_extract_decoded(Pointer encseq, String buffer,
      NativeLong frompos, NativeLong topos);

  public native NativeLong gt_encseq_seqlength(Pointer encseq, NativeLong seqnum);

  public native NativeLong gt_encseq_seqstartpos(Pointer encseq,
      NativeLong seqnum);

  public native NativeLong gt_encseq_seqnum(Pointer encseq, NativeLong position);

  public native String gt_encseq_description(Pointer encseq, Pointer desclen,
      NativeLong seqnum);

  public native Pointer gt_encseq_alphabet(Pointer encseq);

  public native Pointer gt_encseq_filenames(Pointer encseq);

  public native NativeLong gt_encseq_num_of_files(Pointer encseq);

  public native long gt_encseq_effective_filelength(Pointer encseq,
      NativeLong filenum);

  public native void gt_encseq_delete(Pointer encseq);

  /*------------------------------GtEncseqReader------------------------------*/

  public native void gt_encseq_reader_reinit_with_readmode(Pointer esr,
      Pointer encseq, int readmode, NativeLong startpos);

  public native char gt_encseq_reader_next_encoded_char(Pointer esr);

  public native char gt_encseq_reader_next_decoded_char(Pointer esr);

  public native void gt_encseq_reader_delete(Pointer esr);

  /*------------------------------GtEncseqEncoder------------------------*/

  public native Pointer gt_encseq_encoder_new();

  public native int gt_encseq_encoder_use_representation(Pointer ee,
      String sat, Pointer err);

  public native int gt_encseq_encoder_use_symbolmap_file(Pointer ee,
      String smap, Pointer err);

  public native void gt_encseq_encoder_set_logger(Pointer ee, Pointer l);

  public native void gt_encseq_encoder_enable_description_support(Pointer ee);

  public native void gt_encseq_encoder_disable_description_support(Pointer ee);

  public native void gt_encseq_encoder_enable_multiseq_support(Pointer ee);

  public native void gt_encseq_encoder_disable_multiseq_support(Pointer ee);

  public native void gt_encseq_encoder_create_esq_tab(Pointer ee);

  public native void gt_encseq_encoder_do_not_create_esq_tab(Pointer ee);

  public native void gt_encseq_encoder_create_des_tab(Pointer ee);

  public native void gt_encseq_encoder_do_not_create_des_tab(Pointer ee);

  public native void gt_encseq_encoder_create_ssp_tab(Pointer ee);

  public native void gt_encseq_encoder_do_not_create_ssp_tab(Pointer ee);

  public native void gt_encseq_encoder_create_sds_tab(Pointer ee);

  public native void gt_encseq_encoder_do_not_create_sds_tab(Pointer ee);

  public native void gt_encseq_encoder_set_input_dna(Pointer ee);

  public native void gt_encseq_encoder_set_input_protein(Pointer ee);

  public native int gt_encseq_encoder_encode(Pointer ee, Pointer seqfiles,
      String indexname, Pointer err);

  public native void gt_encseq_encoder_delete(Pointer ee);

  /*------------------------------GtEncseqLoader------------------------*/

  public native Pointer gt_encseq_loader_new();

  public native void gt_encseq_loader_require_description_support(Pointer el);

  public native void gt_encseq_loader_drop_description_support(Pointer el);

  public native void gt_encseq_loader_require_multiseq_support(Pointer el);

  public native void gt_encseq_loader_drop_multiseq_support(Pointer el);

  public native void gt_encseq_loader_require_esq_tab(Pointer el);

  public native void gt_encseq_loader_do_not_require_esq_tab(Pointer el);

  public native void gt_encseq_loader_require_des_tab(Pointer el);

  public native void gt_encseq_loader_do_not_require_des_tab(Pointer el);

  public native void gt_encseq_loader_require_ssp_tab(Pointer el);

  public native void gt_encseq_loader_do_not_require_ssp_tab(Pointer el);

  public native void gt_encseq_loader_require_sds_tab(Pointer el);

  public native void gt_encseq_loader_do_not_require_sds_tab(Pointer el);

  public native void gt_encseq_loader_set_logger(Pointer el, Pointer logger);

  public native Pointer gt_encseq_loader_load(Pointer el, String indexname,
      Pointer err);

  public native void gt_encseq_loader_delete(Pointer el);

  /*------------------------------GtEncseqBuilder------------------------*/

  public native Pointer gt_encseq_builder_new(Pointer alpha);

  public native void gt_encseq_builder_enable_description_support(Pointer eb);

  public native void gt_encseq_builder_disable_description_support(Pointer eb);

  public native void gt_encseq_builder_enable_multiseq_support(Pointer eb);

  public native void gt_encseq_builder_disable_multiseq_support(Pointer eb);

  public native void gt_encseq_builder_create_des_tab(Pointer eb);

  public native void gt_encseq_builder_do_not_create_des_tab(Pointer eb);

  public native void gt_encseq_builder_create_ssp_tab(Pointer eb);

  public native void gt_encseq_builder_do_not_create_ssp_tab(Pointer eb);

  public native void gt_encseq_builder_create_sds_tab(Pointer eb);

  public native void gt_encseq_builder_do_not_create_sds_tab(Pointer eb);

  public native void gt_encseq_builder_add_cstr(Pointer eb, String str,
      NativeLong strlen, String desc);

  public native void gt_encseq_builder_add_encoded(Pointer eb, char[] str,
      NativeLong strlen, String desc);

  public native void gt_encseq_builder_set_logger(Pointer eb, Pointer logger);

  public native Pointer gt_encseq_builder_build(Pointer eb, Pointer err);

  public native void gt_encseq_builder_reset(Pointer eb);

  public native void gt_encseq_builder_delete(Pointer eb);

  /*------------------------------GtLTRdigestStream------------------------*/
  public native Pointer gt_ltrdigest_stream_new(Pointer in_stream,
      int tests_to_run, Pointer encseq, PBSOptions pbs_opts,
      PPTOptions ppt_opts, PdomOptions pdom_opts, Pointer err_p);

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

  public native void gt_node_stream_delete(Pointer node_stream);

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
  public native Pointer gt_script_wrapper_visitor_new(
      GT.VISITORFUNC comment_node_visit_func,
      GT.VISITORFUNC feature_node_visit_func,
      GT.VISITORFUNC region_node_visit_func,
      GT.VISITORFUNC sequence_node_visit_func, Pointer ffunc);

  /*------------------------------GtNodeVisitor------------------------------*/
  public native Pointer gt_comment_node_new(String comment);

  public native String gt_comment_node_get_comment(Pointer node);

  /*------------------------------GtRegionNode------------------------------*/
  public native Pointer gt_region_node_new(Pointer seqid, NativeLong start,
      NativeLong end);

  /*------------------------------GtSequenceNode------------------------------*/
  public native Pointer gt_sequence_node_new(String description, Pointer seq_ptr);

  public native String gt_sequence_node_get_description(Pointer node);

  public native String gt_sequence_node_get_sequence(Pointer node);

  public native NativeLong gt_sequence_node_get_sequence_length(Pointer node);

  /*------------------------------GtBioseq------------------------------*/
  public native Pointer gt_bioseq_new(String sequence_file, Pointer err_p);

  public native NativeLong gt_bioseq_number_of_sequences(Pointer bioseq);

  public native String gt_bioseq_get_description(Pointer bioseq, NativeLong idx);

  public native NativeLong gt_bioseq_get_sequence_length(Pointer bioseq,
      NativeLong idx);

  public native void gt_bioseq_delete(Pointer bioseq_ptr);

  private static GTMapping instance = null;

  private GTMapping() {
    /* initialize globals in this library instance */
    gt_lib_init();
  }

  public synchronized static GTMapping getInstance() {
    if (instance == null) {
      instance = new GTMapping();
    }
    return instance;
  }

  protected synchronized void finalize() {
    /* finalize globals in this library instance */
    gt_lib_clean();
  }

}
