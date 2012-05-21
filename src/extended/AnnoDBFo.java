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

import gtnative.GT;
import core.Array;
import core.GTerror;
import core.GTerrorJava;
import core.Range;
import annotationsketch.FeatureIndex;

public class AnnoDBFo extends AnnoDBSchema {

	public AnnoDBFo() {
		super.adb_ptr = GT.INSTANCE.gt_anno_db_fo_new();
	}
	
	/**
	 * Fetches all segments from the database for various filter options.
	 * 
	 * @param gfi The FeatuerIndex.
	 * @param results An array containing the segments.
	 * @param trackId The track name.
	 * @param seqid The chromosome.
	 * @param qryRange The start and end positions on the chromosome.
	 * @param lowerTh The lower intensity threshold.
	 * @param upperTh The upper intensity threshold.
	 * @param sorted Determines if the segments are sorted with respect to 
	 * 			their microarray study.
	 * @param projectFilter Array of Project IDs.
	 * @param plength Length of project array.
	 * @param organFilter Array of organ IDs.
	 * @param olength  Length of organ array.
	 * @param experimentFilter Array of experiment IDs.
	 * @param elength Length of experiment array.
	 * @return
	 * @throws GTerrorJava
	 */
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
											            int elength) throws GTerrorJava{
		
		GTerror err = new GTerror();
		
		int r = GT.INSTANCE.gt_feature_index_fo_get_segments_for_range(gfi.to_ptr(),
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
		
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
		
		return r;
		
	}
	                                
	/**
	 *  Fetches a range over all segments overlapping with a given from the
	 *  database for various filter options.
	 * 
	 * @param gfi The FeatuerIndex.
	 * @param seqid The chromosome.
	 * @param qryRange The given range.
	 * @param maxRange the returned range.
	 * @param lowerTh The lower intensity threshold.
	 * @param upperTh The upper intensity threshold.
	 * @param projectFilter Array of Project IDs.
	 * @param plength Length of project array.
	 * @param organFilter Array of organ IDs.
	 * @param olength  Length of organ array.
	 * @param experimentFilter Array of experiment IDs.
	 * @param elength Length of experiment array.
	 * @return
	 * @throws GTerrorJava
	 */
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
											            int elength) throws GTerrorJava {
		GTerror err = new GTerror();
		
		int r = GT.INSTANCE.gt_feature_index_fo_get_maximal_overlapping_segment_range(gfi.to_ptr(),
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
		
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
		
		return r;
	}
}
