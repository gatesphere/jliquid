// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

public class JLiquidEdge<T, U> extends JLiquidAbstractNode<T, U> {
  public JLiquidFilterFunction<T> filter;
  private JLiquidNode destination;

  public void registerDestination(JLiquidNode dest) { this.destination = dest; }
  public boolean isEmpty() { return this.data == null; }
  
  @SuppressWarnings("unchecked")
  public void registerFilter(JLiquidFilterFunction<T> f) {
    this.filter = f;
    f.registerParent(this.parent);
  }
  
  @SuppressWarnings("unchecked")
  public boolean condition(Object in) {
    if (in == null) return false;
    return this.filter.operate((T)in);
  }
  
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
