// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

import suschord.jliquid.*;

public abstract class JLiquidAbstractNode<T, U> extends JLiquidAbstractEntity {
  public JLiquidFunction<T,U> body;
  protected Object data = null;
  protected boolean marked = false;
  
  protected void mark() { this.marked = true; }
  protected void unmark() { this.marked = false; }
  
  public boolean isEmpty() {
    return (this.data == null);
  }
  
  @SuppressWarnings("unchecked")
  public void registerBody(JLiquidFunction<T, U> b) {
    this.body = b;
    b.registerParent(this.parent);
  }

  public void feed(Object in) { this.unmark(); this.data = in; }
  
  public abstract void load();

  @SuppressWarnings("unchecked")
  public void calculate() {
    if (this.data != null)
      this.data = this.body.operate((T)this.data);
  }
}