// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

public abstract class JLiquidFunction<T, U> extends JLiquidAbstractEntity {
  public abstract U operate(T in);
}
