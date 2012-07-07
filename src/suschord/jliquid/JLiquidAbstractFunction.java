// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

import suschord.jliquid.*;

public abstract class JLiquidAbstractFunction {
  private JLiquidTask parent;
  
  public void registerParent(JLiquidTask p) { this.parent = p; }
}