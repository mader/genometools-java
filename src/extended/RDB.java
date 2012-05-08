package extended;

import gtnative.GT;

import com.sun.jna.Pointer;

public abstract class RDB {
	
	protected Pointer rdb_ptr;
	
	public Pointer to_ptr() {
	    return this.rdb_ptr;
	 }
	
	public synchronized void delete() {
		GT.INSTANCE.gt_rdb_delete(this.rdb_ptr);
	}
}
