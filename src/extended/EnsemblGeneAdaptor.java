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

import com.sun.jna.Pointer;
import com.sun.jna.StringArray;
import com.sun.jna.ptr.PointerByReference;

import core.Array;
import core.GTerror;
import core.GTerrorJava;
import core.Range;

public class EnsemblGeneAdaptor {

	protected Pointer ga_ptr;
	
	public EnsemblGeneAdaptor(int version) throws GTerrorJava {
		
		
		this.ga_ptr = GT.INSTANCE.gt_ensembl_gene_adaptor_new(version);
		
	}
	
	/**
	 * Fetch all genes for a given range.
	 * 
	 * @param fis The FeatureIndex.
	 * @param seqid The chromosome.
	 * @param qryRange The start and end positions.
	 * @param a filter for gene biotypes;
	 * @return An Array containing segments as FeatureNodes.
	 * @throws GTerrorJava
	 */
	public Array fetchGenesForRange(FeatureIndex fis, String seqid, Range qryRange, String[] biotypeFilter) throws GTerrorJava{
		
		Array results = new Array(Pointer.SIZE);
		
		GTerror err = new GTerror();
		
		StringArray strArr = new StringArray(biotypeFilter);
		
		GT.INSTANCE.gt_ensembl_fetch_genes_for_range(this.ga_ptr,
														fis.to_ptr(),
														results.to_ptr(),
														seqid,
														qryRange,
														strArr,
														biotypeFilter.length,
														err.to_ptr());
		
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
		
		return results;
	}
	
	/**
	 * Fetches a a gene for the given gene name.
	 * 
	 * @param gfi The FeatureIndex.
	 * @param geneName The gene name.
	 * @return A Gene as FeatureNode.
	 * @throws GTerrorJava
	 */
	public FeatureNode fetchGeneForSymbol(FeatureIndex gfi, String geneName) throws GTerrorJava{
		
		GTerror err = new GTerror();
		
		PointerByReference gn_ptr = new PointerByReference();
		
		GT.INSTANCE.gt_ensembl_fetch_gene_for_symbol(this.ga_ptr,
														gfi.to_ptr(),
														gn_ptr,
														geneName,
														err.to_ptr());
		
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
		
		return new FeatureNode(gn_ptr.getValue());
	}
	
	/**
	 * Fetches a gene for an Ensembl stable ID.
	 * 
	 * @param gfi The FeatureIndex.
	 * @param stableId The given Ensembl stable ID.
	 * @return A Gene as FeatureNode.
	 * @throws GTerrorJava
	 */
	public FeatureNode fetchGeneForStableId(FeatureIndex gfi, String stableId) throws GTerrorJava{
		
		GTerror err = new GTerror();
		
		PointerByReference gn_ptr = new PointerByReference();
		
		GT.INSTANCE.gt_ensembl_fetch_gene_for_stable_id(this.ga_ptr,
														gfi.to_ptr(),
														gn_ptr,
														stableId,
														err.to_ptr());
		
		if(err.is_set()){
			throw new GTerrorJava(err.get_err());
		}
		
		return new FeatureNode(gn_ptr.getValue());
	}
	
	void delete(){
		GT.INSTANCE.gt_ensembl_gene_adaptor_delete(this.ga_ptr);
	}
	
	public Pointer to_ptr() {
	    return this.ga_ptr;
	 }
}
