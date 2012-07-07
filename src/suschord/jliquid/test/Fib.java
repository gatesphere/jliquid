// jliquid
// Jacob M. Peck
// License: see license/license.txt

package suschord.jliquid.test;

import java.util.*;
import suschord.jliquid.*;

public class Fib {
  public static void main(String[] args) {
    // create a clock
    JLiquidClock clock = new JLiquidClock();
    
    // create a task
    JLiquidTask fib_machine = new JLiquidTask();
    
    // register the task to the clock
    clock.registerTask(fib_machine);
    
    // create input and output ports for the machine, and register them
    JLiquidPort input = new JLiquidPort();
    JLiquidPort output = new JLiquidPort();
    
    fib_machine.setInputPort(input);
    fib_machine.setOutputPort(output);
    
    // create some nodes
    JLiquidNode start = new JLiquidNode();
    JLiquidNode talk = new JLiquidNode();
    JLiquidNode end = new JLiquidNode();
    
    // and some edges
    JLiquidEdge calc = new JLiquidEdge();
    JLiquidEdge done = new JLiquidEdge();
    JLiquidEdge bye = new JLiquidEdge();
    
    // define some behaviors
    JLiquidFunction<Object, Object> echo = new JLiquidFunction<Object, Object>() {
      public Object operate(Object in) { return in; }
    };
    
    JLiquidFunction<Object, Object> speak = new JLiquidFunction<Object, Object>() {
      public Object operate(Object in) {
        this.out(in);
        return in;
      }
    };
    
    JLiquidFunction<Object, Object> empty = new JLiquidFunction<Object, Object>() {
      public Object operate(Object in) { return null; }
    };
    
    JLiquidFunction<ArrayList<Integer>, ArrayList<Integer>> nextfib = new JLiquidFunction<ArrayList<Integer>, ArrayList<Integer>>() {
      public ArrayList<Integer> operate(ArrayList<Integer> in) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        int a = in.get(0);
        int b = in.get(1);
        int c = in.get(2);
        int n = a + b;
        out.add(b);
        out.add(n);
        out.add(--c);
        return out;
      }
    };
    
    JLiquidFunction<ArrayList<Integer>, Integer> prepare = new JLiquidFunction<ArrayList<Integer>, Integer>() {
      public Integer operate(ArrayList<Integer> in) { return in.get(1); }
    };
    
    JLiquidFilterFunction<Object> acceptall = new JLiquidFilterFunction<Object>() {
      public boolean operate(Object in) { return true; }
    };
    
    JLiquidFilterFunction<ArrayList<Integer>> calcfilter = new JLiquidFilterFunction<ArrayList<Integer>>() {
      public boolean operate(ArrayList<Integer> in) {
        return in.get(2) != null && in.get(2) != 0;
      }
    };
    
    // register the nodes to the task
    fib_machine.registerNode(start);
    fib_machine.registerNode(talk);
    fib_machine.registerNode(end);
    
    // assign behaviors
    start.registerBody(echo);
    talk.registerBody(speak);
    end.registerBody(empty);
    
    start.registerEdge(calc);
    start.registerEdge(done);
    talk.registerEdge(bye);
    
    calc.registerBody(nextfib);
    calc.registerFilter(calcfilter);
    calc.registerDestination(start);
    done.registerBody(prepare);
    done.registerFilter(acceptall);
    done.registerDestination(talk);
    bye.registerBody(echo);
    bye.registerFilter(acceptall);
    bye.registerDestination(end);
    
    // add some initial input
    ArrayList<Integer> x = new ArrayList<Integer>();
    x.add(1); // fib num 1
    x.add(1); // fib num 2
    x.add(5); // looking for the 5th fib num
    fib_machine.addInput(x);
    
    // some explanation:
    System.out.println("This machine calculates the 5th Fibonacci number (should be 13).");
    
    // start the machine!
    clock.start();
    
    // get the output
    System.out.println(output.pop());
  }
}
