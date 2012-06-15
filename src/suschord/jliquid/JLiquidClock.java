// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid;

import java.util.*;
import suschord.jliquid.*;

public class JLiquidClock {
  private ArrayList<JLiquidTask> tasks = new ArrayList<JLiquidTask>();
  private boolean running = false;
  private int tickNum = 0;
  
  private boolean tickEdge() { return this.tickNum % 2 == 0; }
  public boolean posEdge() { return this.tickEdge(); }
  public boolean negEdge() { return !this.tickEdge(); }
  
  public void registerTask(JLiquidTask task) { this.tasks.add(task); }
  public void deregisterTask(JLiquidTask task) { this.tasks.remove(task); }
  
  public void start() { this.run(); }
  public void stop() { this.running = false; }
  
  private void run() {
    this.running = true;
    while(this.running && this.workToDo())
      this.tick();
  }
  
  private boolean workToDo() {
    if (this.tasks.size() == 0) return false;
    for (JLiquidTask t : this.tasks)
      if (t.workToDo()) return true;
    return false;
  }
  
  private void tick() {
    this.tickNum++;
    if (this.negEdge())
      for (JLiquidTask t : this.tasks)
        t.load();
    else
      for (JLiquidTask t : this.tasks)
        t.calculate();
  }
}
