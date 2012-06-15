// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

public interface JLiquidFunction<T, U> {
  public U operate(T in);
}
