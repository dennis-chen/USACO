/*
ID: dennis.8
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;

class barn1 {

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int maxBoards = Integer.parseInt(st.nextToken());
    int stalls = Integer.parseInt(st.nextToken());
    int cows = Integer.parseInt(st.nextToken());
    int[] locations = new int[cows];
    for(int i = 0; i < cows; i++){
        int cow = Integer.parseInt(f.readLine());
        locations[i] = cow;
    }
    Arrays.sort(locations);
    int[] gaps = new int[cows-1];
    int prevCow = locations[0];
    for(int i = 0; i < gaps.length; i++){
        int cow = locations[i+1];
        gaps[i] = cow-prevCow-1;
        prevCow = cow;
    }
    Arrays.sort(gaps);
    int stallsCovered = 0;
    for(int i = 0; i < cows-maxBoards; i++){
        stallsCovered += gaps[i];
    }
    stallsCovered += cows;
    //System.out.println(stallsCovered);
    out.println(stallsCovered);
    out.close();
  }

}
