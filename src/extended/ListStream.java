package extended;

import java.util.List;
import java.util.ListIterator;

public class ListStream extends CustomStream {
  private ListIterator<GenomeNode> source_it;
  
  public ListStream(List<GenomeNode> source) {
    this.source_it = source.listIterator();
  }
  
  @Override
  protected GenomeNode next() {
    GenomeNode ret = null;
    if (source_it.hasNext()) {
      ret = source_it.next();
    }
    return ret;
  }
}
