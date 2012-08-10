/*
  Copyright (c) 2012 Malte Mader <mader@zbh.uni-hamburg.de>
  Copyright (c) 2012 Center for Bioinformatics, University of Hamburg

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

package extended;

import com.sun.jna.Pointer;
import com.sun.jna.StringArray;

import gtnative.GT;
import core.Array;
import core.GTerror;
import core.GTerrorJava;
import core.Range;
import annotationsketch.FeatureIndexFo;

public class AnnoDBFo extends AnnoDBSchema {

	public AnnoDBFo() {
		super.adb_ptr = GT.INSTANCE.gt_anno_db_fo_new();
	}
	
	public void  segmentOnly(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_filter_segment_only(fifo.to_ptr());
	}
	
	public void  mutationsOnly(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_filter_mutations_only(fifo.to_ptr());
	}
	
	public void  resetType(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_reset_filter_type(fifo.to_ptr());
	}

	public void  unsetAllFilters(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_unset_all_filters(fifo.to_ptr());
	}

	public void setSegmentsLowerTh(FeatureIndexFo fifo, double lower_th){
		GT.INSTANCE.gt_feature_index_fo_set_segments_lower_th(fifo.to_ptr(), lower_th);
	}
	
	public void unsetSegmentsLowerTh(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_unset_segments_lower_th(fifo.to_ptr());
	}

	public void setSegmentsUpperTh(FeatureIndexFo fifo, double upper_th){
		GT.INSTANCE.gt_feature_index_fo_set_segments_upper_th(fifo.to_ptr(), upper_th);
	}
	
	public void unsetSegmentsUpperTh(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_unset_segments_upper_th(fifo.to_ptr());
	}

	public void setTrackId(FeatureIndexFo fifo, String track_id){
		GT.INSTANCE.gt_feature_index_fo_set_track_id(fifo.to_ptr(), track_id);
	}
	
	public void unsetTrackId(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_unset_track_id(fifo.to_ptr());
	}

	public void setSegmentsSorted(FeatureIndexFo fifo, boolean sorted){
		GT.INSTANCE.gt_feature_index_fo_set_segments_sorted(fifo.to_ptr(), sorted);
	}

	public void setScore(FeatureIndexFo fifo,
	                                   double score,
	                                   boolean grater_than){
		GT.INSTANCE.gt_feature_index_fo_set_score(fifo.to_ptr(), score, grater_than);
	}

	public void unsetScore(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_unset_score(fifo.to_ptr());
	}

	public void addWhereClauseIntFilter(FeatureIndexFo fifo,
	                                                     String column,
	                                                     int[] filter){
		GT.INSTANCE.gt_feature_index_fo_add_where_clause_int_filter(fifo.to_ptr(), column, filter, filter.length);
	}
	
	public void addWhereClauseStrFilter(FeatureIndexFo fifo,
	                                                     String column,
	                                                     String[] filter){
		
		StringArray strArr = new StringArray(filter);
		
		GT.INSTANCE.gt_feature_index_fo_add_where_clause_str_filter(fifo.to_ptr(), column, strArr, filter.length);
	}

	public void addWhereClauseDoubleFilter(FeatureIndexFo fifo,
												String column,
												double[] filter){
		
		GT.INSTANCE.gt_feature_index_fo_add_where_clause_double_filter(fifo.to_ptr(), column, filter, filter.length);
	}
	
	public void resetWhereClauseIntFilter(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_reset_where_clause_int_filter(fifo.to_ptr());
	}
	
	public void resetWhereClauseStrFilter(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_reset_where_clause_str_filter(fifo.to_ptr());
	}

	public void resetWhereClauseDoubleFilter(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_reset_where_clause_double_filter(fifo.to_ptr());
	}
	
	public void addProjectFilter(FeatureIndexFo fifo,
	                                            int[] filter){
		GT.INSTANCE.gt_feature_index_fo_add_project_filter(fifo.to_ptr(), filter, filter.length);
	}
	
	public void addTissueFilter(FeatureIndexFo fifo,
	                                           int[] filter){
		GT.INSTANCE.gt_feature_index_fo_add_tissue_filter(fifo.to_ptr(), filter, filter.length);
	}

	public void setAdditionalExperimentFilter(FeatureIndexFo fifo,
	                                                          int[] filter){
		GT.INSTANCE.gt_feature_index_fo_set_additional_experiment_filter(fifo.to_ptr(), filter, filter.length);
	}
	                                                          
	public void unsetAdditionalExperimentFilter(FeatureIndexFo fifo){
		GT.INSTANCE.gt_feature_index_fo_unset_additional_experiment_filter(fifo.to_ptr());
	}
	
	public void addSomaticFilter(FeatureIndexFo fifo,
									String[] filter){
		
		StringArray strArr = new StringArray(filter);
		
		GT.INSTANCE.gt_feature_index_fo_add_somatic_filter(fifo.to_ptr(), strArr, filter.length);
	}
	
	public void addConfidenceFilter(FeatureIndexFo fifo,
										String[] filter){
		StringArray strArr = new StringArray(filter);
		
		GT.INSTANCE.gt_feature_index_fo_add_confidence_filter(fifo.to_ptr(), strArr, filter.length);
	}
	
	public void addSnptoolFilter(FeatureIndexFo fifo,
									String[] filter){
		StringArray strArr = new StringArray(filter);
		
		GT.INSTANCE.gt_feature_index_fo_add_snptool_filter(fifo.to_ptr(), strArr, filter.length);
	}
	
	public Array getFeatures(FeatureIndexFo fifo,
	                                     String seqid,
	                                     Range range) throws GTerrorJava{
		
		GTerror err = new GTerror();
		
		Array results = new Array(Pointer.SIZE);
		
		GT.INSTANCE.gt_feature_index_fo_get_features(fifo.to_ptr(),
															results.to_ptr(),
															seqid,
															range,
															err.to_ptr());
		
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
		
		return results;
	}
	                                                    
	public Array processMutations(Array mutations,
	                             RDB rdb,
	                             String trackId,
	                             String[] biotypeFilter) throws GTerrorJava{
		
		GTerror err = new GTerror();
		
		Array results = new Array(Pointer.SIZE);
		
		StringArray strArr = new StringArray(biotypeFilter);
		
		GT.INSTANCE.gt_feature_index_fo_process_mutations(results.to_ptr(),
																	mutations.to_ptr(),
																	rdb.to_ptr(),
																	trackId,
																	strArr,
																	biotypeFilter.length,
																	err.to_ptr());
		
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
		
		return results;
	}
}