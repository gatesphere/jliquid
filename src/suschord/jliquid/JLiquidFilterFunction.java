// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

import suschord.jliquid.*;

public abstract class JLiquidFilterFunction<T> extends JLiquidAbstractFunction {
  public abstract boolean operate(T in);
}
