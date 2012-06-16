// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

import java.util.*;
import suschord.jliquid.*;

public class JLiquidNode<T, U> {
  public JLiquidFunction<T,U> body;
  private ArrayList<JLiquidEdge> edges = new ArrayList<JLiquidEdge>();
  private Object data = null;
  private JLiquidTask parent = null;
  
  public boolean isEmpty() {
    if (this.data != null) return false;
    for(JLiquidEdge e : this.edges)
      if (!e.isEmpty())
        return false;
    return true;
  }
  
  public void registerEdge(JLiquidEdge edge) {
    this.edges.add(edge);
    edge.registerParent(this);
  }
  
  public void registerParent(JLiquidTask task) { this.parent = task; }
  public void feed(Object in) { this.data = in; }
  
  public void load() {
    for (JLiquidEdge e : this.edges) e.load();
    if (this.data != null) {
      for (JLiquidEdge e : this.edges)
        if (e.condition(this.data)) {
          e.feed(this.data);
          this.data = nil;
          break;
        }
    }
    // raise exception
    /*
    if (this.data != null && this.edges.size() != 0)
      ...
    */
  }
  
  public void calculate() {
    if (this.data != null)
      this.data = this.body.operate(this.data);
    for (JLiquidEdge e : this.edges)
      e.calculate();
  }
  
}
