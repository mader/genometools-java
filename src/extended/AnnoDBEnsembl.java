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
import core.GTerror;
import core.GTerrorJava;

public class AnnoDBEnsembl extends AnnoDBSchema {

	public AnnoDBEnsembl() {
		
		super.adb_ptr = GT.INSTANCE.gt_anno_db_ensembl_new();
	}

	public int getFeatureIndexEnsemblVersion(FeatureIndex fis) throws GTerrorJava {
		
		int version;
		
		GTerror err = new GTerror();
		
		version = GT.INSTANCE.gt_anno_db_feature_index_get_version(fis.to_ptr(), err.to_ptr());
		
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
		
		return version;
	}
}