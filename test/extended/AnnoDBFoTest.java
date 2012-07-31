package extended;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jna.Pointer;

import annotationsketch.FeatureIndex;
import annotationsketch.FeatureIndexFo;

import core.Array;
import core.GTerrorJava;
import core.Range;

import junit.framework.TestSuite;

public class AnnoDBFoTest extends TestSuite {

static RDB rdbfo;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		RDBMysql rdb = null;
		try {
			rdb = new RDBMysql("localhost", 3306, "testoracle", "fouser", "fish4me");
		} catch (GTerrorJava e) {
			e.printStackTrace();
		}
		rdbfo = (RDB) rdb;
	}
	
	@Test
	public void TestFetchSegments(){
		
		AnnoDBFo adb = new AnnoDBFo();
		FeatureIndex fi = adb.gt_anno_db_schema_get_feature_index(rdbfo);
		FeatureIndexFo fifo = new FeatureIndexFo(fi.to_ptr());
		
		int[] projectFilter = {1};
		
		Array results = null;
		
		Range r = new Range(88986342, 90713681);
		
		adb.segmentOnly(fifo);
		adb.setSegmentsLowerTh(fifo, -0.9);
		adb.setTrackId(fifo, "test1");
		adb.setSegmentsSorted(fifo, false);
		adb.addProjectFilter(fifo, projectFilter, projectFilter.length);
		
		try {
			results = adb.getFeatures(fifo, "10", r);
		} catch (GTerrorJava e) {
			e.printStackTrace();
		}
		
		assertTrue(results.size() == 11);
		//extend...
		
		FeatureNode fn;
		
		for(int i = 0; i < results.size(); i++){
			
			fn = new FeatureNode(results.get(i));
		
			fn.dispose();
		}
		
		results.dispose();
		fifo.delete();
		adb.delete();
		
	}
	
	@Test
	public void TestFetchMutations(){
		
		AnnoDBFo adb = new AnnoDBFo();
		FeatureIndex fi = adb.gt_anno_db_schema_get_feature_index(rdbfo);
		FeatureIndexFo fifo = new FeatureIndexFo(fi.to_ptr());
		
		int[] projectFilter = {1};
		
		Array results = null;
		
		Range r = new Range(88000000, 92000000);
		
		adb.mutationsOnly(fifo);
		adb.setTrackId(fifo, "test1");
		adb.addProjectFilter(fifo, projectFilter, projectFilter.length);
		
		try {
			results = adb.getFeatures(fifo, "10", r);
		} catch (GTerrorJava e) {
			e.printStackTrace();
		}
		
		assertTrue(results.size() == 5);
		//extend...
		
		FeatureNode fn;
		
		for(int i = 0; i < results.size(); i++){
			
			fn = new FeatureNode(results.get(i));
		
			fn.dispose();
		}
		
		results.dispose();
		fifo.delete();
		adb.delete();
		
	}
	
	@Test
	public void TestProcessMutations(){
		
		RDBMysql rdb = null;
		try {
			rdb = new RDBMysql("localhost", 3306, "ensembl66", "fouser", "fish4me");
		} catch (GTerrorJava e) {
			e.printStackTrace();
		}
		RDB rdbe = (RDB) rdb;
		
		AnnoDBFo adb = new AnnoDBFo();
		FeatureIndex fi = adb.gt_anno_db_schema_get_feature_index(rdbfo);
		FeatureIndexFo fifo = new FeatureIndexFo(fi.to_ptr());
		
		Array testResults = new Array(Pointer.SIZE);
		Array sortedResults = null;
		String[] biofil = new String[0];
		FeatureNode fn;
		
		try {
			fn = new FeatureNode("10", "mutation", 89625776, 89625776, ".");
			fn.add_attribute("STUDY_ID", "1");
			fn.add_attribute("ID", "test1");
			fn.add_attribute("NAME", "1");
			fn.add_attribute("ROOT", "NO");
			
			testResults.add(fn);
			
			fn = new FeatureNode("10", "mutation", 99632776, 99632776, ".");
			fn.add_attribute("STUDY_ID", "1");
			fn.add_attribute("ID", "test1");
			fn.add_attribute("NAME", "2");
			fn.add_attribute("ROOT", "NO");
			
			testResults.add(fn);
			
			fn = new FeatureNode("10", "mutation", 89903054, 89903054, ".");
			fn.add_attribute("STUDY_ID", "1");
			fn.add_attribute("ID", "test1");
			fn.add_attribute("NAME", "3");
			fn.add_attribute("ROOT", "NO");
			
			testResults.add(fn);
			
			sortedResults = adb.processMutations(testResults, rdbe, biofil);
			
		} catch (GTerrorJava e) {
			e.printStackTrace();
		}
		
		assertTrue(sortedResults.size() == 3);
		
		Range rng; 
		
		FeatureNodeIteratorDepthFirst fni = new FeatureNodeIteratorDepthFirst(new FeatureNode(sortedResults.get(0)));
			
		fn = fni.next();
			
		assertTrue(fn.get_seqid().equals("10"));
		assertTrue(fn.get_type().equals("gene"));
			
		assertTrue(fn.get_attribute("NOF_MUTATIONS").equals("1"));
			
		rng = fn.get_range();
			
		assertTrue(rng.get_start() == 99624757);
		assertTrue(rng.get_end() == 99790585);
			
		assertTrue(fn.get_attribute("ID").equals("CRTAC1"));
		assertTrue(fn.get_attribute("NAME").equals("ENSG00000095713"));
		assertTrue(fn.get_attribute("ROOT").equals("NO"));
			
		fn = fni.next();
			
		assertTrue(fn.get_seqid().equals("10"));
		assertTrue(fn.get_type().equals("mutation"));
			
		rng = fn.get_range();
			
		assertTrue(rng.get_start() == 99632776);
		assertTrue(rng.get_end() == 99632776);
			
		assertTrue(fn.get_attribute("STUDY_ID").equals("1"));
		assertTrue(fn.get_attribute("ID").equals("test1"));
		assertTrue(fn.get_attribute("NAME").equals("2"));
		assertTrue(fn.get_attribute("ROOT").equals("NO"));
		
		fni.dispose();
		
		fni = new FeatureNodeIteratorDepthFirst(new FeatureNode(sortedResults.get(1)));
		
		fn = fni.next();
			
		assertTrue(fn.get_seqid().equals("10"));
		assertTrue(fn.get_type().equals("gene"));
			
		assertTrue(fn.get_attribute("NOF_MUTATIONS").equals("1"));
			
		rng = fn.get_range();
			
		assertTrue(rng.get_start() == 89622870);
		assertTrue(rng.get_end() == 89731687);
			
		assertTrue(fn.get_attribute("ID").equals("PTEN"));
		assertTrue(fn.get_attribute("NAME").equals("ENSG00000171862"));
		assertTrue(fn.get_attribute("ROOT").equals("NO"));
			
		fn = fni.next();
			
		assertTrue(fn.get_seqid().equals("10"));
		assertTrue(fn.get_type().equals("mutation"));
			
		rng = fn.get_range();
			
		assertTrue(rng.get_start() == 89625776);
		assertTrue(rng.get_end() == 89625776);
			
		assertTrue(fn.get_attribute("STUDY_ID").equals("1"));
		assertTrue(fn.get_attribute("ID").equals("test1"));
		assertTrue(fn.get_attribute("NAME").equals("1"));
		assertTrue(fn.get_attribute("ROOT").equals("NO"));
		
		fni.dispose();
		
		fn = new FeatureNode(sortedResults.get(2));
			
		assertTrue(fn.get_seqid().equals("10"));
		assertTrue(fn.get_type().equals("mutation"));
			
		assertTrue(fn.get_attribute("NOF_MUTATIONS").equals("1"));
			
		rng = fn.get_range();
			
		assertTrue(rng.get_start() == 89903054);
		assertTrue(rng.get_end() == 89903054);
			
		assertTrue(fn.get_attribute("STUDY_ID").equals("1"));
		assertTrue(fn.get_attribute("ID").equals("test1"));
		assertTrue(fn.get_attribute("NAME").equals("3"));
		assertTrue(fn.get_attribute("ROOT").equals("NO"));
		
		for(int i = 0; i < sortedResults.size(); i++){
			
			fn = new FeatureNode(sortedResults.get(i));
		
			fn.dispose();
		}
		
		for(int i = 0; i < testResults.size(); i++){
			
			fn = new FeatureNode(testResults.get(i));
		
			fn.dispose();
		}
		
		testResults.dispose();
		sortedResults.dispose();
		fifo.dispose();
		adb.delete();
		rdbe.delete();
	}
	
	@AfterClass
	public static void tearDown() {
		rdbfo.delete();
	}
}
