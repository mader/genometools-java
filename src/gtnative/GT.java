package gtnative;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import core.GTerrorJava;
import core.Range;

public interface GT extends Library
{
	GT INSTANCE = new GTMapping();

	/*------------------------------GtGenomeNode------------------------------*/
	int gt_genome_node_accept(Pointer gn, Pointer gv, Pointer err);
	Pointer gt_genome_node_ref(Pointer gn);
	NativeLong gt_genome_node_get_start(Pointer gn);
	NativeLong gt_genome_node_get_end(Pointer gn);
	String gt_genome_node_get_filename(Pointer gn);
	void gt_genome_node_delete(Pointer gn);
	void gt_genome_node_change_seqid(Pointer genome_node, Pointer GtStr);

	/*------------------------------GtFeatureNode------------------------------*/
	Pointer gt_feature_node_new(Pointer seqid, String type, NativeLong start,
			NativeLong end, int strand);
	void gt_feature_node_add_child(Pointer parent, Pointer child);
	String gt_feature_node_get_source(Pointer feature_node);
	void gt_feature_node_set_source(Pointer feature_node, Pointer source);
	String gt_feature_node_get_type(Pointer feature_node);
	int gt_feature_node_has_type(Pointer feature_node, String type);
	int gt_feature_node_score_is_defined(Pointer feature_node);
	float gt_feature_node_get_score(Pointer feature_node);
	void gt_feature_node_set_score(Pointer feature_node, float score);
	void gt_feature_node_unset_score(Pointer feature_node);
	int gt_feature_node_get_strand(Pointer feature_node);
	void gt_feature_node_set_strand(Pointer feature_node, int strand);
	int gt_feature_node_get_phase(Pointer feature_node);
	void gt_feature_node_set_phase(Pointer feature_node, int strand);
	String gt_feature_node_get_attribute(Pointer feature_node, String name);
	void gt_feature_node_add_attribute(Pointer feature_node, String tag,
			String value);
	Pointer gt_feature_node_get_attribute_list(Pointer feature_node);
	void gt_genome_node_mark(Pointer genome_node);
	boolean gt_genome_node_is_marked(Pointer genome_node);

	/*------------------------------GtLayout------------------------------*/
	Pointer gt_layout_new(Pointer diagram, NativeLong width, Pointer gt_style, Pointer err);
	NativeLong gt_layout_get_height(Pointer gt_lay_ptr);
	int gt_layout_sketch(Pointer gt_lay_ptr, Pointer target_canvas, Pointer err);
	void gt_layout_delete(Pointer gt_lay_ptr);

	/*------------------------------GtDiagram------------------------------*/
	Pointer gt_diagram_new(Pointer feat_index, String seqid, Range gt_range,
			Pointer gt_style, Pointer gt_err);
	Pointer gt_diagram_new_from_array(Pointer gt_array, Range gt_range,
			Pointer gt_style);
	void gt_diagram_set_track_selector_func(Pointer gt_diagram,
			TRACKSELECTOR func);
	void gt_diagram_reset_track_selector_func(Pointer diagram_ptr);
	void gt_diagram_delete(Pointer gt_diagram);

	interface TRACKSELECTOR extends Callback
	{
		void callback(Pointer block_ptr, Pointer str_ptr, Pointer data_ptr) throws GTerrorJava;
	}

	/*------------------------------GtBlock------------------------------*/
	Pointer gt_block_ref(Pointer gt_block);
	Range gt_block_get_range_ptr(Pointer gt_block);
	String gt_block_get_type(Pointer gt_block);
	int gt_block_has_only_one_fullsize_element(Pointer gt_block);
	void gt_block_merge(Pointer gt_block, Pointer gt_block_sec);
	Pointer gt_block_clone(Pointer gt_block);
	void gt_block_set_strand(Pointer gt_block, int i);
	Pointer gt_block_get_top_level_feature(Pointer gt_block);
	int gt_block_get_strand(Pointer gt_block);
	NativeLong gt_block_get_size(Pointer gt_block);
	void gt_block_delete(Pointer gt_block);

	/*CanvasCairoFile*/
    Pointer gt_canvas_cairo_file_new(Pointer style, int output_type, NativeLong width, NativeLong height, Pointer image_info);
    int gt_canvas_cairo_file_to_file(Pointer canvas, String filename, Pointer err);
	void gt_canvas_delete(Pointer canvas);

	/*------------------------------GtFeatureIndex------------------------------*/
    Pointer gt_feature_index_memory_new();
    Pointer gt_feature_index_memory_get_node_by_ptr(Pointer fim, NativeLong id, Pointer err);
    void gt_feature_index_delete(Pointer fi);
    void gt_feature_index_add_feature_node(Pointer feature_index,
        Pointer feature_node);
    int gt_feature_index_add_gff3file(Pointer fi, String gff3file, Pointer err);
    Pointer gt_feature_index_get_features_for_seqid(Pointer fi, String seqid);
    String gt_feature_index_get_first_seqid(Pointer fi);
    Pointer gt_feature_index_get_seqids(Pointer fi);
    void gt_feature_index_get_range_for_seqid(Pointer fi, Range rng,
        String seqid);
    int gt_feature_index_has_seqid(Pointer fi, String seqid);

    /*------------------------------GtImageInfo------------------------------*/
    Pointer gt_image_info_new();
    NativeLong gt_image_info_get_height(Pointer ii_ptr);
    NativeLong gt_image_info_num_of_rec_maps(Pointer ii_ptr);
    Pointer gt_image_info_get_rec_map(Pointer ii_ptr, NativeLong i);
    void gt_image_info_delete(Pointer ii_ptr);

    /*------------------------------GtRecMap------------------------------*/
    double gt_rec_map_get_northwest_x(Pointer rec_map);
    double gt_rec_map_get_northwest_y(Pointer rec_map);
    double gt_rec_map_get_southeast_x(Pointer rec_map);
    double gt_rec_map_get_southeast_y(Pointer rec_map);
    Pointer gt_rec_map_get_genome_feature(Pointer rec_map);
    Pointer gt_rec_map_ref(Pointer rec_map);
    void gt_rec_map_delete(Pointer rec_map);
    int gt_rec_map_has_omitted_children(Pointer rec_map);

    /*------------------------------GtStyle------------------------------*/
    Pointer gt_style_new(Pointer err);
    int gt_style_load_file(Pointer style, String str, Pointer err);
    int gt_style_load_str(Pointer style, Pointer str, Pointer err);
    int gt_style_to_str(Pointer style, Pointer str, Pointer err);
    // feature_node Object should be set to null as long as it isn't used
    boolean gt_style_get_color(Pointer style, String sect, String key,
        Pointer color, Pointer feat_node);
    void gt_style_set_color(Pointer style, String sect, String key,
        Pointer color);
    // feature_node Object should be set to null as long as it isn't used
    boolean gt_style_get_str(Pointer style, String sect, String key,
        Pointer str, Pointer feat_node);
    void gt_style_set_str(Pointer style, String sect, String key, Pointer str);
    // feature_node Object should be set to null as long as it isn't used
    boolean gt_style_get_num(Pointer style, String sect, String key,
        DoubleByReference i, Pointer feat_node);
    void gt_style_set_num(Pointer style, String sect, String key,
        DoubleByReference i);
    // feature_node Object should be set to null as long as it isn't used
    boolean gt_style_get_bool(Pointer style, String sect, String key,
        IntByReference b, Pointer feat_node);
    void gt_style_set_bool(Pointer style, String sect, String key, int b);
    void gt_style_unset(Pointer style, String sect, String key);
    void gt_style_delete(Pointer style);

    /*------------------------------GtArray------------------------------*/
    Pointer gt_array_new(NativeLong size_of_elem);
    Pointer gt_array_get(Pointer array, NativeLong index);
    void gt_array_add_ptr(Pointer array, Pointer elem);
    NativeLong gt_array_size(Pointer array);
    void gt_array_delete(Pointer array);
    Pointer gt_array_ref(Pointer array);

    /*------------------------------GtError------------------------------*/
    Pointer gt_error_new();
    String gt_error_get(Pointer err);
    Boolean gt_error_is_set(Pointer err);
    void gt_error_unset(Pointer err);
    void gt_error_delete(Pointer err);

    /*------------------------------GtStr------------------------------*/
    Pointer gt_str_new();
    Pointer gt_str_new_cstr(String cstr);
    Pointer gt_str_ref(Pointer s);
    void gt_str_append_str(Pointer dest, Pointer src);
    void gt_str_append_cstr(Pointer dest, String str);
    String gt_str_get(Pointer s);
    NativeLong gt_str_length(Pointer str);
    void gt_str_delete(Pointer str);

    /*------------------------------GtStrArray------------------------------*/
    Pointer gt_str_array_new();
    void gt_str_array_add_cstr(Pointer str_array, String cstr);
    String gt_str_array_get(Pointer str_array, NativeLong strnum);
    NativeLong gt_str_array_size(Pointer str_array);
    void gt_str_array_delete(Pointer str_array);

    /*------------------------------GtRange------------------------------*/
    NativeLong gt_range_length(Range rng);

    /*------------------------------GtFeatureNodeIterator------------------------------*/
    Pointer gt_feature_node_iterator_next(Pointer gn);
    void gt_feature_node_iterator_delete(Pointer gn);
    Pointer gt_feature_node_iterator_new(Pointer gn);
    Pointer gt_feature_node_iterator_new_direct(Pointer gn);

    /*------------------------------GtGenomeStream------------------------------*/
    int gt_node_stream_next(Pointer node_stream, PointerByReference genome_node, Pointer err);

    /*------------------------------GtGFF3InStream------------------------------*/
    Pointer gt_gff3_in_stream_new_sorted(String filename);
    Pointer gt_gff3_in_stream_get_used_types(Pointer node_stream);
}
