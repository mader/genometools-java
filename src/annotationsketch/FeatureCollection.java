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

package annotationsketch;

import com.sun.jna.Pointer;

import core.Array;

import extended.GenomeNode;
import gtnative.GT;

public class FeatureCollection {

	protected Pointer feature_collection_ptr;
	
	public FeatureCollection() {
		feature_collection_ptr = GT.INSTANCE.gt_feature_collection_new();
	}
	
	public FeatureCollection(Array arr) {
		feature_collection_ptr =  GT.INSTANCE.gt_feature_collection_new_from_array(arr.to_ptr());
	}
	
	public void add(GenomeNode gn){
		GT.INSTANCE.gt_feature_collection_add(this.feature_collection_ptr, gn.to_ptr());
	}
	
	public void addArray(Array arr){
		GT.INSTANCE.gt_feature_collection_add_array(this.feature_collection_ptr, arr.to_ptr());
	}
	
	public GenomeNode get(int index){
		return new GenomeNode(GT.INSTANCE.gt_feature_collection_get(this.feature_collection_ptr, index));
	}
	
	public long size(){
		return GT.INSTANCE.gt_feature_collection_size(this.feature_collection_ptr).longValue();
	}
	
	Array toArray(){
		return new Array(GT.INSTANCE.gt_feature_collection_to_array(this.feature_collection_ptr));
	}
	
	public void delete(){
		GT.INSTANCE.gt_feature_collection_delete(this.feature_collection_ptr);
	}
	
	public Pointer to_ptr() {
	    return this.feature_collection_ptr;
	  }
}