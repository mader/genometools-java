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

import core.Array;
import core.GTerror;
import core.GTerrorJava;
import core.Range;
import gtnative.GT;
import annotationsketch.FeatureIndex;

public class EnsemblKaryoAdaptor {

	protected Pointer ka_ptr;
	
	public EnsemblKaryoAdaptor(int version) throws GTerrorJava {
		
		this.ka_ptr = GT.INSTANCE.gt_ensembl_karyo_adaptor_new(version);
		
	}
	
	/**
	 * Fetches the Range of a given karyoband.
	 * 
	 * @param gfi The FeatureIndex.
	 * @param chr The chromosome.
	 * @param band The band.
	 * @return The range of the karyoband.
	 * @throws GTerrorJava
	 */
	public Range fetchRangeForKaryoband(FeatureIndex gfi,
										String chr,
										String band) throws GTerrorJava {
		
		GTerror err = new GTerror();
		Range r = new Range();
		GT.INSTANCE.gt_ensembl_fetch_range_for_karyoband(this.ka_ptr,
															gfi.to_ptr(),
															r,
															chr,
															band,
															err.to_ptr());
		
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
		
		return r;
	}
	
	/**
	 * Fetches karyobands that overlap with a given range.
	 * 
	 * @param gfi The FeatureIndex.
	 * @param seqid The chromosome.
	 * @param qryRange The given Range.
	 * @return An array of karyobands as FeatureNode.
	 * @throws GTerrorJava
	 */
	public Array fetchKaryobandsForRange(FeatureIndex gfi,
												String seqid,
												Range qryRange) throws GTerrorJava {
		
		Array results = new Array(Pointer.SIZE);
		
		GTerror err = new GTerror();
		
		GT.INSTANCE.gt_ensembl_fetch_karyobands_for_range(this.ka_ptr,
															gfi.to_ptr(),
															results.to_ptr(),
															seqid,
															qryRange,
															err.to_ptr());
		
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
		
		return results;
	}
	
	void delete(){
		GT.INSTANCE.gt_ensembl_karyo_adaptor_delete(this.ka_ptr);
	}
	
	public Pointer to_ptr() {
	    return this.ka_ptr;
	 }
}