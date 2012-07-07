// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

import java.util.*;
import suschord.jliquid.*;

public class JLiquidNode<T, U> extends JLiquidAbstractNode<T, U> {
  private ArrayList<JLiquidEdge> edges = new ArrayList<JLiquidEdge>();
  
  protected void mark() {
    for (JLiquidEdge e : this.edges) e.mark();
    this.marked = true;
  }
  
  protected void unmark() {
    for (JLiquidEdge e : this.edges) e.unmark();
    this.marked = false;
  }
  
  public boolean isEmpty() {
    if (this.data != null) return false;
    for(JLiquidEdge e : this.edges)
      if (!e.isEmpty())
        return false;
    return true;
  }
  
  public void registerEdge(JLiquidEdge edge) {
    this.edges.add(edge);
    edge.registerParent(this.parent);
  }
  
  public void load() {
    for (JLiquidEdge e : this.edges) e.load();
    if (this.marked && (this.data != null)) {
      for (JLiquidEdge e : this.edges)
        if (e.condition(this.data)) {
          e.feed(this.data);
          this.data = null;
          break;
        }
    }
    // raise exception
    /*
    if (this.data != null && this.edges.size() != 0)
      ...
    */
  }
  
  @SuppressWarnings("unchecked")
  public void calculate() {
    if (this.data != null)
      this.data = this.body.operate((T)this.data);
    for (JLiquidEdge e : this.edges)
      e.calculate();
  }
  
}
