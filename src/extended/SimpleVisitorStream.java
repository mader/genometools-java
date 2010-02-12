/*
  Copyright (c) 2010 Sascha Steinbiss <steinbiss@zbh.uni-hamburg.de>
  Copyright (c) 2010 Center for Bioinformatics, University of Hamburg

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

public class SimpleVisitorStream extends CustomStream {
  private NodeVisitor visitor;
  private GenomeStream in_stream;

  public SimpleVisitorStream(GenomeStream in_stream, NodeVisitor visitor) {
    this.visitor = visitor;
    this.in_stream = in_stream;
  }

  @Override
  protected GenomeNode next() throws Exception {
    GenomeNode next_node = in_stream.next_tree();
    if (next_node != null) {
      next_node.accept(this.visitor);
    }
    return next_node;
  }
}
