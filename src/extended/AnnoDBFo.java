package extended;

import gtnative.GT;
import core.Array;
import core.GTerror;
import core.Range;
import annotationsketch.FeatureIndex;

public class AnnoDBFo extends AnnoDBSchema {

	public AnnoDBFo() {
		super.adb_ptr = GT.INSTANCE.gt_anno_db_fo_new();
	}
	
	public int gt_feature_index_fo_get_segments_for_range(FeatureIndex gfi,
	                                                    Array results,
	                                                    String trackId,
	                                                    String seqid,
	                                                    Range qryRange,
	                                                    double lowerTh,
											            double upperTh,
											            boolean sorted,
											            int[] projectFilter,
											            int plength,
											            int[] organFilter,
											            int olength,
											            int[] experimentFilter,
											            int elength){
		
		GTerror err = new GTerror();
		
		GT.INSTANCE.gt_feature_index_fo_get_segments_for_range(gfi.to_ptr(),
				results.to_ptr(),
				trackId,
				seqid,
				qryRange,
				lowerTh,
				upperTh,
				sorted,
				projectFilter,
				plength,
				organFilter,
				olength,
				experimentFilter,
				elength,
				err.to_ptr());
		
		return 0;
		
	}
	                                                    
	public int gt_feature_fo_index_get_maximal_overlapping_segment_range(FeatureIndex gfi,
	                                                    String seqid,
	                                                    Range qryRange,
	                                                    Range maxRange,
	                                                    double lowerTh,
											            double upperTh,
											            int[] projectFilter,
											            int plength,
											            int[] organFilter,
											            int olength,
											            int[] experimentFilter,
											            int elength,
	                                                    GTerror err) {
		
		return GT.INSTANCE.gt_feature_index_fo_get_maximal_overlapping_segment_range(gfi.to_ptr(),
				seqid,
				qryRange,
				maxRange,
				lowerTh,
				upperTh,
				projectFilter,
				plength,
				organFilter,
				olength,
				experimentFilter,
				elength,
				err.to_ptr());
	}
}
