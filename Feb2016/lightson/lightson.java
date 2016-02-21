/*
ID: dennis.8
LANG: JAVA
TASK: lightson
*/
import java.io.*;
import java.util.*;

class lightson {

  public static int N;
  public static boolean[][] lit;
  public static boolean[][] visited;
  public static boolean[][] added;
  public static int litCount;
  public static ArrayList<int[]> nodes = new ArrayList<int[]>();
  public static ArrayList<int[]> allNodes = new ArrayList<int[]>();
  public static Entry[][] switches;
  public static int[][] dirs  = new int[][]{{0,1,0,-1},
                                        {1,0,-1,0}};

  public static class Entry {
    public ArrayList<int[]> entries = new ArrayList<int[]>();
    public Entry(ArrayList<int[]> e){
      this.entries = e;
    }
    public void add(int[] t){
      this.entries.add(t);
    }
    public String toString(){
      String res=  "";
      for(int[] c: entries){
        res += "["+c[0]+","+c[1]+"]";
      }
      return res;
    }
  }

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("lightson.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    N = Integer.parseInt(st.nextToken());
    lit = new boolean[N+1][N+1];
    visited = new boolean[N+1][N+1];
    added = new boolean[N+1][N+1];
    switches = new Entry[N+1][N+1];
    int M = Integer.parseInt(st.nextToken());
    for(int i = 0; i < M; i++){
      st = new StringTokenizer(f.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if(switches[x][y] == null){
        ArrayList<int[]> first = new ArrayList<int[]>();
        first.add(new int[]{a,b});
        switches[x][y] = new Entry(first);
      } else {
        switches[x][y].add(new int[]{a,b});
      }
    }
    lit[1][1] = true;
    nodes.add(new int[]{1,1});
    allNodes.add(new int[]{1,1});
    BFS();
    int count = 0;
    for(boolean[] b: lit){
      for(boolean e: b){
        if(e){
          count++;
        }
      }
      //System.out.println(Arrays.toString(b));
    }
    System.out.println(count);
    out.println(count);
    out.close();
  }

  public static void BFS(){
    while(nodes.size() > 0){
      for(int[] n: nodes){
        System.out.println("in light loop");
        System.out.println(n[0]+" "+n[1]);
        litCount += 1;
        switchLights(n);
        visited[n[0]][n[1]] = true;
      }
      ArrayList<int[]> toAdd = new ArrayList<int[]>();
      for(int[] n: allNodes){
        for(int[] d: dirs){
          int[] neighbor = new int[]{n[0]+d[0],n[1]+d[1]};
          if(isValid(neighbor)){
            System.out.println("new neighbor");
            System.out.println(neighbor[0]+" "+neighbor[1]);
            if(!added[neighbor[0]][neighbor[1]]){
              toAdd.add(neighbor);
              added[neighbor[0]][neighbor[1]] = true;
            }
          }
        }
      }
      allNodes.addAll(toAdd);
      nodes = toAdd;
    }
  }

  public static void switchLights(int[] n){
    if(switches[n[0]][n[1]] != null){
      ArrayList<int[]> ls = switches[n[0]][n[1]].entries;
      for(int[] l: ls){
        lit[l[0]][l[1]] = true;
      }
    }
  }

  public static boolean isValid(int[] n){
    return n[0] > 0 && n[0] <= N && n[1] > 0 && n[1] <= N &&
      lit[n[0]][n[1]] && !visited[n[0]][n[1]];
  }

}
