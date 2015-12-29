/*
ID: dennis.8
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;

class milk {

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("milk.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int milkNeed = Integer.parseInt(st.nextToken());
    int farmNum = Integer.parseInt(st.nextToken());
    FarmInfo[] farms = new FarmInfo[farmNum];
    for(int i = 0; i < farmNum; i++){
        st = new StringTokenizer(f.readLine());
        int cost = Integer.parseInt(st.nextToken());
        int avail = Integer.parseInt(st.nextToken());
        farms[i] = new FarmInfo(avail,cost);
    }
    Arrays.sort(farms, new CustomComparator());
    int moneySpent = 0;
    int farmIndex = 0;
    while(milkNeed > 0){
        int c = farms[farmIndex].cost;
        int a = farms[farmIndex].avail;
        int milkTaken = Math.min(milkNeed,a);
        moneySpent += milkTaken*c;
        milkNeed -= milkTaken;
        farmIndex++;
    }
    //System.out.println(Arrays.toString(farms));
    //System.out.println(moneySpent);
    out.println(moneySpent);
    out.close();
  }

  public static class FarmInfo{
      int avail;
      int cost;
      public FarmInfo(int a, int c){
          this.avail = a;
          this.cost = c;
      }
      public String toString(){
          return cost + " " + avail;
      }
  }

  static class CustomComparator implements Comparator<FarmInfo> {
    @Override
    public int compare(FarmInfo m1, FarmInfo m2) {
        return m1.cost - m2.cost;
    }
  }
}
