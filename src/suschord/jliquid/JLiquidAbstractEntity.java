// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

import suschord.jliquid.*;

public abstract class JLiquidAbstractEntity {
  protected JLiquidTask parent;
  public void registerParent(JLiquidTask p) { this.parent = p; }
  
  public void out(Object o) { this.parent.getOutputPort().push(o); }
  public void error(Object o) { this.parent.getErrorPort().push(o); }
  public void die() { this.parent.die(); }
  public void detach() { this.parent.detach(); }
}
