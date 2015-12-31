/*
ID: dennis.8
LANG: JAVA
TASK: wormhole
*/
import java.io.*;
import java.util.*;

class wormhole {

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
    int wormholes = Integer.parseInt(f.readLine());
    int[] enter = new int[wormholes];
    Integer[] exit = new Integer[wormholes];
    HashMap<Integer,ArrayList<Wormhole>> ys = new HashMap<Integer, ArrayList<Wormhole>>();
    ArrayList<Integer> holes = new ArrayList<Integer>();
    for(int i = 0; i < wormholes; i++){
        holes.add(i);
        StringTokenizer st = new StringTokenizer(f.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        if(ys.containsKey(y)){
            ArrayList<Wormhole> xs = ys.get(y);
            xs.add(new Wormhole(x,i));
        } else {
            ArrayList<Wormhole> newList = new ArrayList<Wormhole>();
            newList.add(new Wormhole(x,i));
            ys.put(y, newList);
        }
    }
    for(Integer y: ys.keySet()){
        ArrayList<Wormhole> xs = ys.get(y);
        Collections.sort(xs, new CustomComparator());
        for(int i = 1; i < xs.size(); i++){
            int prevId = xs.get(i-1).id;
            int currId = xs.get(i).id;
            exit[prevId] = currId;
        }
    }
    int cycles = findCycles(exit, enter, holes);
    System.out.println(cycles);
    out.println(cycles);
    out.close();
  }

  public static int findCycles(Integer[] exit, int[] enter, ArrayList<Integer> holes){
      if(holes.size() == 0){
        //System.out.println("exit:");
        //System.out.println(Arrays.toString(exit));
        //System.out.println("enter: ");
        //System.out.println(Arrays.toString(enter));
        boolean cycleExists = checkCycle(exit, enter);
        return cycleExists? 1:0;
      }
      int holeA = 0;
      int cyclesFound = 0;
      for(int i = 1; i < holes.size(); i++){
          int[] newEnter = enter.clone();
          int holeB = i;
          ArrayList<Integer> newHoles = new ArrayList(holes);
          Integer b = newHoles.remove(holeB);
          Integer a = newHoles.remove(holeA);
          newEnter[a] = b;
          newEnter[b] = a;
          cyclesFound += findCycles(exit, newEnter, newHoles);
      }
      return cyclesFound;
  }

  public static boolean checkCycle(Integer[] exit, int[] enter){
      for(int i = 0; i < enter.length; i++){
          Integer nextNode = i;
          HashSet<Integer> entered = new HashSet<Integer>();
          boolean entering = true;
          while(true){
              if(entering){
                  if(entered.contains(enter[nextNode])){
                      return true;
                  } else {
                      nextNode = enter[nextNode];
                      entered.add(nextNode);
                  }
              } else {
                  if(exit[nextNode] == null){
                      break;
                  }
                  nextNode = exit[nextNode];
              }
              entering = !entering;
          }
      }
      return false;
  }

  public static class Wormhole{
      int x;
      int id;
      public Wormhole(int x, int id){
          this.x = x;
          this.id = id;
      }
      public String toString(){
          return "id: "+id+ " x: "+x;
      }
  }

  static class CustomComparator implements Comparator<Wormhole> {
    @Override
    public int compare(Wormhole m1, Wormhole m2) {
        return m1.x - m2.x;
    }
  }
}
