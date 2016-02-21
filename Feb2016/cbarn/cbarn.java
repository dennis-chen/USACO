/*
ID: dennis.8
LANG: JAVA
TASK: cbarn
*/
import java.io.*;
import java.util.*;


class cbarn {

  public static PrintWriter out;
  public static int[] rooms;
  public static int n;
  public static int min = Integer.MAX_VALUE;

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("cbarn.in"));
    n = Integer.parseInt(f.readLine());
    rooms = new int[n];
    for(int i = 0 ; i < n; i++){
      rooms[i] = Integer.parseInt(f.readLine());
    }
    for(int i = 0 ; i < n; i++){
      tryEnter(i);
    }
    out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
    out.println(min);
    System.out.println(min);
    out.close();
  }

  public static void tryEnter(int roomIndex){
    //calculates cost of having cows enter at room index
    int cost = 0;
    for(int i = 1; i+roomIndex < n; i++){
      cost += rooms[i+roomIndex]*i;
    }
    for(int i = 0; i < roomIndex; i++){
      cost += rooms[i]*(n-roomIndex+i);
    }
    min = Math.min(min,cost);
  }

}
