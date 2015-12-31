/*
ID: dennis.8
LANG: JAVA
TASK: numtri
*/
import java.io.*;
import java.util.*;

class numtri {

    public static int[][] tri;

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
    int rows = Integer.parseInt(f.readLine());
    tri = new int[rows][];
    for(int i = 0; i < rows; i++){
        StringTokenizer st = new StringTokenizer(f.readLine());
        tri[i] = new int[i+1];
        for(int j = 0; j < i+1; j++){
            int num = Integer.parseInt(st.nextToken());
            tri[i][j] = num;
        }
    }
    for(int i = rows-2; i > -1; i--){
        int[] lower = tri[i+1];
        int[] upper = tri[i];
        for(int j = 0; j < upper.length; j++){
            upper[j] = upper[j] + Math.max(lower[j],lower[j+1]);
        }
    }
    //for(int i = 0; i < rows; i++){
    //    for(int j = 0; j < i+1; j++){
    //        System.out.print(tri[i][j]);
    //        System.out.print(" ");
    //    }
    //    System.out.print("\n");
    //}
    //System.out.println(tri[0][0]);
    out.println(tri[0][0]);
    out.close();
  }
  
}
