// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

import java.util.*;
import suschord.jliquid.*;

public class JLiquidPort {
  private LinkedList<Object> contents = new LinkedList<Object>();
  
  public void push(Object in) { this.contents.add(in); }
  public Object pop() { this.contents.poll(); }
  public int size() { this.contents.size(); }
}
