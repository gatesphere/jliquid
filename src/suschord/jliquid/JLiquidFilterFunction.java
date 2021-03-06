// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

import suschord.jliquid.*;

public abstract class JLiquidFilterFunction<T> extends JLiquidAbstractEntity {
  public abstract boolean operate(T in);
}
