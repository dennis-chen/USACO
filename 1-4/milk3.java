/*
ID: dennis.8
LANG: JAVA
TASK: milk3
*/
import java.io.*;
import java.util.*;

class milk3 {

  public static boolean[][][] stateSeen = new boolean[21][21][21];
  public static boolean[] cVals = new boolean[21];
  public static int[] capacities = new int[3];

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    capacities[0] = Integer.parseInt(st.nextToken());
    capacities[1] = Integer.parseInt(st.nextToken());
    capacities[2] = Integer.parseInt(st.nextToken());
    cVals[capacities[2]] = true;
    int[] buckets = new int[3];
    buckets[2] = capacities[2];
    pourMilk(buckets,1);
    String res = "";
    for(int i = 0; i < cVals.length; i++){
        if(cVals[i]){
            res+=" "+i;
        }
    }
    res = res.substring(1);
    //System.out.println(res);
    out.println(res);
    out.close();
  }

  public static void pourMilk(int[] buckets, int depth){
      if(stateSeen[buckets[0]][buckets[1]][buckets[2]]){
          return;
      }
      stateSeen[buckets[0]][buckets[1]][buckets[2]] = true;
      if(buckets[0] == 0 && depth > 1){
          cVals[buckets[2]] = true;
      }
      for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
          if(i != j){
              //pour i into j
              int[] newBuckets = buckets.clone();
              int toPour = Math.min(buckets[i],capacities[j]-buckets[j]);
              newBuckets[i] -= toPour;
              newBuckets[j] += toPour;
              pourMilk(newBuckets, depth+1);
          }
      }}
      return;
  }
  
}
