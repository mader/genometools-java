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
import static org.junit.Assert.*;

public class StrTest
{

  @BeforeClass
  public static void init() {
  }

  @Test
  public void test_to_ptr()
  {
    Str s = new Str("ATATAT");
    assertTrue(s.to_ptr() != null);
    s.dispose();
  }

  @Test
  public void test_length()
  {
    Str s = new Str("ATATAT");
    assertTrue(s.length() == 6);
    s.dispose();
  }

  @Test
  public void test_to_s()
  {
    Str s = new Str("ATATAT");
    assertTrue(s.to_s().equals("ATATAT"));
    String string = s.to_s();
    assertTrue(string.equals("ATATAT"));
    assertTrue(!s.to_s().equals("GCGCGC"));
    s.dispose();
  }

  @Test
  public void test_append_str()
  {
    Str s = new Str("ATATAT");
    Str s2 = new Str("GCGCGC");
    s.append_str(s2);
    assertTrue(s.to_s().equals("ATATATGCGCGC"));
    assertTrue(!s.to_s().equals("GCGCGCATATAT"));
    assertTrue(s.length() == 12);
    s.dispose();
    s2.dispose();
  }

  @Test
  public void test_cmp_str()
  {
    Str s = new Str("ATATAT");
    Str s2 = new Str("GCGCGC");
    assertTrue(s.str_cmp(s2) < 0);
    assertTrue(s2.str_cmp(s) > 0);
    s.dispose();
    s2.dispose();
  }
  
  @Test
  public void testStrPointer()
  {
    Str s = new Str("ATATAT");
    Str s2 = new Str(s.to_ptr());
    assertTrue(s2.to_s().equals("ATATAT"));
    assertTrue(!s2.to_s().equals("ATAT"));
    assertTrue(s.to_ptr().equals(s2.to_ptr()));
    s.dispose();
    s2.dispose();
  }

  @Test
  public void testStrString()
  {
    Str s = new Str("ATATAT");
    assertTrue(s.length() == 6);
    assertTrue(s.to_s().equals("ATATAT"));
    s.dispose();
  }
}
