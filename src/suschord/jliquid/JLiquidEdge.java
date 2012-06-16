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

  public void registerDestination(JLiquidNode dest) { this.destination = dest; }
  public boolean isEmpty() { return this.data == null; }
  public void registerParent(JLiquidNode node) { this.parent = node; }
  
  public boolean condition(Object in) {
    if (in instanceof T)
      return this.filter.operate((T)in);
    return false;
  }
  
  public void feed(Object in) { this.data = in; }
  
  public void load() {
    if (this.data != null) {
      this.destination.feed(this.data);
      this.data = null;
    }
  }
  
  public void calculate() {
    if (this.data != null)
      if (this.data instanceof T)
        this.data = this.body.operate((T)this.data);
      // else
      //   raise type mismatch exception
  }
  
}
