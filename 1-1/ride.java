/*
ID: dennis.8
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class ride {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    StringTokenizer st = new StringTokenizer(f.readLine());
    String cometName = st.nextToken();
    StringTokenizer st2 = new StringTokenizer(f.readLine());
    String groupName = st2.nextToken();
    boolean nameMatch = matches(cometName, groupName);
    System.out.println(nameMatch);
    if(nameMatch){
        out.println("GO");
    } else {
        out.println("STAY");
    }
    out.close();
  }

  public static boolean matches(String c, String g){
      int cometNum = 1;
      int groupNum = 1;
      for(int i = 0; i < c.length(); i++){
          char ch = c.charAt(i);
          cometNum *= (Character.getNumericValue(ch) - 9);
      }
      for(int i = 0; i < g.length(); i++){
          char ch = g.charAt(i);
          groupNum *= (Character.getNumericValue(ch) - 9);
      }
      return ((cometNum % 47) == (groupNum % 47));
  }
}
