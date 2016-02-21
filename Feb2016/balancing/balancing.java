/*
ID: dennis.8
LANG: JAVA
TASK: balancing
*/
import java.io.*;
import java.util.*;


class balancing {

  public static Coord[] xSorted;
  public static Coord[] ySorted;
  public static PrintWriter out;

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("balancing.in"));
    out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int N = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());
    xSorted = new Coord[N];
    ySorted = new Coord[N];
    for(int i = 0; i < N; i++){
      st = new StringTokenizer(f.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      Coord c = new Coord(x,y);
      xSorted[i] = c;
      ySorted[i] = c;
    }
    Arrays.sort(xSorted, new SortX());
    Arrays.sort(ySorted, new SortY());
    int best = N;
    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++){
        int a = xSorted[i].x-1;
        int b = ySorted[j].y-1;
        best = Math.min(maxCows(a,b),best);
      }
    }
    System.out.println(best);
    out.println(best);
    out.close();
  }

  public static int maxCows(int a, int b){
    assert(a%2 == 0 && b%2 == 0);
    //dividing with x = a and y = b, returns largest num of cows in any of the four sections
    int topLeft = 0;
    int topRight = 0;
    int bottomLeft = 0;
    int bottomRight = 0;
    for(Coord c: xSorted){
      if(c.x > a){
        if(c.y > b){
          topRight++;
        } else {
          bottomRight++;
        }
      } else {
        if(c.y > b){
          topLeft++;
        } else {
          bottomLeft++;
        }
      }
    }
    return Math.max(
        Math.max(bottomLeft,bottomRight),
        Math.max(topLeft,topRight));
  }

  static class Coord {
    int x;
    int y;
    public Coord(int x, int y){
      this.x = x; this.y = y;
    }
    public String toString(){
      return "("+x+","+y+")";
    }
  }

  static class SortX implements Comparator<Coord> {
    @Override
    public int compare(Coord f1, Coord f2) {
      return f1.x-f2.x;
    }
  }

  static class SortY implements Comparator<Coord> {
    @Override
    public int compare(Coord f1, Coord f2) {
      return f1.y-f2.y;
    }
  }

}
