/*
ID: dennis.8
LANG: JAVA
TASK: skidesign
*/
import java.io.*;
import java.util.*;

class skidesign {

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
    int hillNum = Integer.parseInt(f.readLine());
    int[] elevations = new int[hillNum];
    for(int i = 0; i<hillNum; i++){
        elevations[i] = Integer.parseInt(f.readLine());
    }

    int minCost = findMinCost(elevations);
    
    out.println(minCost);
    out.close();
  }

  public static int findMinCost(int[] elevations){
      int minCost = Integer.MAX_VALUE;
      for(int i = 0; i <= 100-17; i++){
          int min = i;
          int max = i+17;
          int cost = 0;
          for(int e: elevations){
              if(e < min){
                  cost += (min-e)*(min-e);
              } else if (e > max){
                  cost += (e-max)*(e-max);
              }
          }
          minCost = Math.min(minCost,cost);
      }
      return minCost;
  }

}
