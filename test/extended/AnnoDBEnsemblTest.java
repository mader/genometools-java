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

import static org.junit.Assert.assertTrue;
import junit.framework.TestSuite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import core.GTerrorJava;
import core.Range;

import annotationsketch.FeatureIndex;

public class AnnoDBEnsemblTest extends TestSuite {

	static RDB rdbe;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		RDBMysql rdb = null;
		try {
			rdb = new RDBMysql("localhost", 3306, "ensembl66", "fouser", "fish4me");
		} catch (GTerrorJava e) {
			e.printStackTrace();
		}
		rdbe = (RDB) rdb;
	}
	
	@Test
	public void TestFetchGenesForRange(){
		
		AnnoDBEnsembl adb = new AnnoDBEnsembl();
		FeatureIndex fi = adb.gt_anno_db_schema_get_feature_index(rdbe);
		
		int ensembl_version;
		EnsemblGeneAdaptor ga = null;
		
		try {
			ensembl_version = adb.getFeatureIndexEnsemblVersion(fi);
			
			ga = new EnsemblGeneAdaptor(ensembl_version);
			
		} catch (GTerrorJava e1) {
			e1.printStackTrace();
		}
		
		Range r = new Range(89595000, 89635000);
		
		FeatureNode fn;
		
		String[] expectedGenes = new String[]{"KLLN","CFL1P1","ATAD1","PTEN"};
		
		core.Array arr = null;
		try {
			String[] str = new String[0];
			arr = ga.fetchGenesForRange(fi, "10", r, str);
		} catch (GTerrorJava e) {
			e.printStackTrace();
		}
		
		assertTrue(arr.size() == 4);
		
		for(int i = 0; i < arr.size(); i++){
		
			fn = new FeatureNode(arr.get(i));
		
			assertTrue(fn.get_attribute("ID").equals(expectedGenes[i]));
		
			fn.dispose();
		}
		
		arr.dispose();
		fi.delete();
		adb.delete();
	}
	
	@Test
	public void TestFetchGeneForSymbol(){
		
		AnnoDBEnsembl adb = new AnnoDBEnsembl();
		FeatureIndex fi = adb.gt_anno_db_schema_get_feature_index(rdbe);
		
		int ensembl_version;
		EnsemblGeneAdaptor ga = null;
		
		try {
			ensembl_version = adb.getFeatureIndexEnsemblVersion(fi);
			
			ga = new EnsemblGeneAdaptor(ensembl_version);
			
		} catch (GTerrorJava e1) {
			e1.printStackTrace();
		}
		
		FeatureNode fn = null;
		
		try {
			fn = ga.fetchGeneForSymbol(fi, "PTEN");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(fn.get_attribute("ID").equals("PTEN"));
		assertTrue(fn.get_attribute("NAME").equals("ENSG00000171862"));
		
		fn.dispose();
		fi.delete();
		adb.delete();
		
	}
	
	@Test
	public void TestFetchGeneForStableId(){
		
		AnnoDBEnsembl adb = new AnnoDBEnsembl();
		FeatureIndex fi = adb.gt_anno_db_schema_get_feature_index(rdbe);
		
		int ensembl_version;
		EnsemblGeneAdaptor ga = null;
		
		try {
			ensembl_version = adb.getFeatureIndexEnsemblVersion(fi);
			
			ga = new EnsemblGeneAdaptor(ensembl_version);
			
		} catch (GTerrorJava e1) {
			e1.printStackTrace();
		}
		
		FeatureNode fn = null;
		
		try {
			fn = ga.fetchGeneForStableId(fi, "ENSG00000171862");
		} catch (GTerrorJava e) {
			e.printStackTrace();
		}
		
		assertTrue(fn.get_attribute("ID").equals("PTEN"));
		assertTrue(fn.get_attribute("NAME").equals("ENSG00000171862"));
		
		fn.dispose();
		fi.delete();
		adb.delete();
		
	}
	
	@Test
	public void TestGetRangeForKaryoband(){
		
		AnnoDBEnsembl adb = new AnnoDBEnsembl();
		FeatureIndex fi = adb.gt_anno_db_schema_get_feature_index(rdbe);
		
		int ensembl_version;
		EnsemblKaryoAdaptor ka = null;
		
		try {
			ensembl_version = adb.getFeatureIndexEnsemblVersion(fi);
			
			ka = new EnsemblKaryoAdaptor(ensembl_version);
			
		} catch (GTerrorJava e1) {
			e1.printStackTrace();
		}
		
		Range rng = null;
		
		try {
			rng = ka.fetchRangeForKaryoband(fi, "10", "q23.31");
		} catch (GTerrorJava e) {
			e.printStackTrace();
		}
		
		assertTrue(rng.get_start() == 89500001);
		assertTrue(rng.get_end() == 92900000);
		
		fi.delete();
		adb.delete();
		
	}
	
	@Test
	public void TestGetKaryobandFeaturesForRange(){
		
		AnnoDBEnsembl adb = new AnnoDBEnsembl();
		FeatureIndex fi = adb.gt_anno_db_schema_get_feature_index(rdbe);
		
		int ensembl_version;
		EnsemblKaryoAdaptor ka = null;
		
		try {
			ensembl_version = adb.getFeatureIndexEnsemblVersion(fi);
			
			ka = new EnsemblKaryoAdaptor(ensembl_version);
			
		} catch (GTerrorJava e1) {
			e1.printStackTrace();
		}
		
		Range qryRange = new Range(90000000, 92000000);
		
		core.Array arr = null;
		FeatureNode fn;
		
		try {
			arr = ka.fetchKaryobandsForRange(fi, "10", qryRange);
		} catch (GTerrorJava e) {
			e.printStackTrace();
		}
		
		assertTrue(arr.size() == 1);
		
		fn = new FeatureNode(arr.get(0));
		Range r = fn.get_range();
		
		assertTrue(r.get_start() == 89500001);
		assertTrue(r.get_end() == 92900000);
		
		fn.dispose();
		arr.dispose();
		fi.delete();
		adb.delete();
		
	}
	
	@AfterClass
	public static void tearDown() {
		rdbe.delete();
	}
}