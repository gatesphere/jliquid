// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

import java.util.*;
import suschord.jliquid.*;

public class JLiquidTask {
  private JLiquidClock parent;
  private JLiquidPort input;
  private JLiquidPort output;
  private JLiquidPort error;
  private ArrayList<JLiquidNode> nodes = new ArrayList<JLiquidNode>();
  private boolean sealed = false;
  
  public boolean isSealed() { return this.sealed; }
  public void seal() { this.sealed = true; }
  public void unseal() { this.sealed = false; }
  
  public boolean isEmpty() {
    for (JLiquidNode n : this.nodes)
      if (!n.isEmpty())
        return false;
    return true;
  }
  
  protected boolean workToDo() { return !(this.isEmpty() && this.input.size() == 0); }
  
  protected void registerParent(JLiquidClock p) { this.parent = p; }
  
  public void registerNode(JLiquidNode node) {
    this.nodes.add(node);
    node.registerParent(this);
  }
  
  public void addInput(Object in) {
    this.input.push(in);
  }

  public void addInputList(List<Object> in) {
    for (Object o : in)
      this.input.push(o);
  }
  
  public void setInputPort(JLiquidPort port) { this.input = port; }
  public JLiquidPort getInputPort() { return this.input; }
  public void setErrorPort(JLiquidPort port) { this.error = port; }
  public JLiquidPort getErrorPort() { return this.error; }
  public void setOutputPort(JLiquidPort port) { this.output = port; }
  public JLiquidPort getOutputPort() { return this.output; }
  
  public void load() {
    for (JLiquidNode n : this.nodes)
      n.mark();
    for (JLiquidNode n : this.nodes)
      n.load();
    for (JLiquidNode n : this.nodes)
      n.unmark();
    if (this.isEmpty() && this.isSealed()) this.unseal();
    if (this.input.size() != 0 && !this.isSealed()) {
      this.nodes.get(0).feed(this.input.pop());
      this.seal();
    }
  }
  
  public void calculate() {
    for (JLiquidNode n : this.nodes)
      n.calculate();
  }
  
  public void die() {
    this.parent.stop();
  }
  
  public void detach() {
    this.parent.deregisterTask(this);
  }
}
