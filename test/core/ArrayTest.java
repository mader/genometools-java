/*
  Copyright (c) 2009 Philipp Carpus  <random234@gmx.net>
  Copyright (c) 2009 Center for Bioinformatics, University of Hamburg

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


package core;
import org.junit.*;

import com.sun.jna.Pointer;

import extended.FeatureNode;
import static org.junit.Assert.*;

public class ArrayTest
{
  static FeatureNode fn;

  @BeforeClass
  public static void init() throws GTerrorJava {
    fn = new FeatureNode("test","type",1000,8000,".");
  }
  
  @AfterClass
  public static void tearDown() {
    fn.dispose();
  }
  
  @Test
  public void test_to_ptr() {
    Array arr = new Array(Pointer.SIZE);
    assertTrue(arr.to_ptr() != null);
    arr.dispose();
  }

  @Test
  public void test_size() {
    // Size of Element is meant to be the size of a Pointer on the Target system
    Array arr = new Array(Pointer.SIZE);
    assertTrue(arr.size() == 0);
    for (int i = 0; i<8 ; i++){
    arr.add(fn); }
    assertTrue(arr.size() == 8);
    assertTrue(arr.size() != 7);
    arr.dispose();
  }
  
  @Test
  public void test_add() {
    Array arr = new Array(Pointer.SIZE);
    arr.add(fn);
    assertTrue(arr.get(0).equals(fn.to_ptr()));
    arr.dispose();
  }

  @Test
  public void test_get() {
    Array arr = new Array(Pointer.SIZE);
    arr.add(fn);
    assertEquals(fn.to_ptr(), arr.get(0));
    arr.dispose();
  }

  @Test
  public void test_ArrayPointer() {
    Array arr = new Array(Pointer.SIZE);
    Array arr2 = new Array(arr.to_ptr());
    arr.add(fn);
    assertTrue(arr.get(0).equals(fn.to_ptr()));
    assertNotNull(arr);
    assertNotNull(arr2);
    arr.dispose();
    arr2.dispose();
  }
}
