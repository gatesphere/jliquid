// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

public class JLiquidEdge<T, U> {
  public JLiquidFunction<T, U> body;
  public JLiquidFilterFunction<T> filter;
  private JLiquidNode destination;
  private Object data;
  private JLiquidNode parent;
  private boolean marked = false;

  protected void mark() { this.marked = true; }
  protected void unmark() { this.marked = false; }

  public void registerDestination(JLiquidNode dest) { this.destination = dest; }
  public boolean isEmpty() { return this.data == null; }
  public void registerParent(JLiquidNode node) { this.parent = node; }
  
  @SuppressWarnings("unchecked")
  public boolean condition(Object in) {
    if (in == null) return false;
    return this.filter.operate((T)in);
  }
  
  public void feed(Object in) { this.unmark(); this.data = in; }
  
  public void load() {
    if (this.data != null && this.marked) {
      this.destination.feed(this.data);
      this.data = null;
    }
  }
  
  @SuppressWarnings("unchecked")
  public void calculate() {
    if (this.data != null) {
      this.data = this.body.operate((T)this.data);
    }
      // else
      //   raise type mismatch exception
  }
  
}
