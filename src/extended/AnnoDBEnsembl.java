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
import annotationsketch.FeatureIndex;
import core.Array;
import core.GTerror;
import core.Range;

public class AnnoDBEnsembl extends AnnoDBSchema {

	public AnnoDBEnsembl() {
		
		super.adb_ptr = GT.INSTANCE.gt_anno_db_ensembl_new();
	}

	public int getFeatureForGeneName(FeatureIndex gfi, GenomeNode gn, String geneName, GTerror err){
		return GT.INSTANCE.gt_feature_index_ensembl_get_feature_for_gene_name(gfi.to_ptr(), gn.to_ptr(), geneName, err.to_ptr());
	}
	
	public int getFeatureForStableId(FeatureIndex gfi, GenomeNode gn, String stableId, GTerror err){
		return GT.INSTANCE.gt_feature_index_ensembl_get_feature_for_stable_id(gfi.to_ptr(), gn.to_ptr(), stableId, err.to_ptr());
	}
	
	public Range getRangeForKaryoband(FeatureIndex gfi, String chr, String band){
		
		GTerror err = new GTerror();
		Range r = new Range();
		GT.INSTANCE.gt_feature_index_ensembl_get_range_for_karyoband(gfi.to_ptr(), r.getPointer(), chr, band, err.to_ptr());
		
		return r;
	}
	
	public int getKaryobandFeaturesForRange(FeatureIndex gfi, Array results, String seqid, Range qryRange, GTerror err){
		return GT.INSTANCE.gt_feature_index_ensembl_get_karyoband_features_for_range(gfi.to_ptr(), results.to_ptr(), seqid,
				qryRange, err.to_ptr());
	}
}