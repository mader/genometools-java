package extended;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import core.GTerrorJava;

public class ListStreamTest {
  private static ArrayList<GenomeNode> nodes;
  private static GenomeNode fn, fn2;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    nodes = new ArrayList<GenomeNode>();
    fn  = (GenomeNode) new FeatureNode("test", "type", 1000, 8000, ".");
    fn2 = (GenomeNode) new FeatureNode("test", "type2", 600, 700, "+");
    nodes.add(fn2);
    nodes.add(fn);
  }
  
  @Test
  public void test_list_stream_array_list() throws GTerrorJava {
    GenomeStream s = new ListStream(nodes);
    GenomeNode n;
    ArrayList<GenomeNode> result_nodes = new ArrayList<GenomeNode>();
    while((n = s.next_tree()) != null) {
      result_nodes.add(n);
    }
    assertTrue(result_nodes.equals(nodes));
  }

}
