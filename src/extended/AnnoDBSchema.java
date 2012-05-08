package extended;

import gtnative.GT;

import annotationsketch.FeatureIndex;

import com.sun.jna.Pointer;

import core.GTerror;

public abstract class AnnoDBSchema {

	protected Pointer adb_ptr;
	
	public Pointer to_ptr() {
	    return adb_ptr;
	 }
	
	public FeatureIndex gt_anno_db_schema_get_feature_index(RDB db) {
		 GTerror err = new GTerror();
		return new FeatureIndex(GT.INSTANCE.gt_anno_db_schema_get_feature_index(this.adb_ptr, db.to_ptr(), err.to_ptr()));
	}
	
	public synchronized void delete() {
		GT.INSTANCE.gt_anno_db_schema_delete(adb_ptr);
	}
}