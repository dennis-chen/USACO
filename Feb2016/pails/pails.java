/*
ID: dennis.8
LANG: JAVA
TASK: pails
*/
import java.io.*;
import java.util.*;


class pails {

  public static PrintWriter out;

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("pails.in"));
    out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int X = Integer.parseInt(st.nextToken()); 
    int Y = Integer.parseInt(st.nextToken()); 
    int M = Integer.parseInt(st.nextToken()); 
    int mostX = M/X;
    int maxAdded = Y;
    for(int i = 0; i <= mostX; i++){
      int xTotal = X*i;
      int remainder = M-xTotal;
      int mostY = remainder/Y;
      int yTotal = mostY*Y;
      maxAdded = Math.max(yTotal+xTotal,maxAdded);
    }
    System.out.println(maxAdded);
    out.println(maxAdded);
    out.close();
  }

}
