/*
ID: dennis.8
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.util.*;


class pprime {

public static ArrayList<String> pals = new ArrayList<String>();

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int a = Integer.parseInt(st.nextToken()); 
    int b = Integer.parseInt(st.nextToken()); 
    //generate all palindromes b/w 1 and 99,999,999 inclusive
    genPals(8);
    genPals(7);
    //convert string palindromes to integers, keeping valid ones
    ArrayList<Integer> intPals = new ArrayList<Integer>();
    for(String p: pals){
        if(p.charAt(0) != '0'){
            int t = Integer.parseInt(p);
            if(t >= a && t <= b){
                intPals.add(t);
            }
        }
    }
    Collections.sort(intPals);
    for(int t: intPals){
        if(isPrime(t)){
            out.println(t);
        }
    }
    out.close();
  }

  public static ArrayList<String> genPals(int val){
      if(val == 2){
        ArrayList<String> res = new ArrayList<String>(Arrays.asList("00","11","22","33","44","55","66","77","88","99"));
        pals.addAll(res);
        return res;
      }
      if(val == 1){
        ArrayList<String> res = new ArrayList<String>(Arrays.asList("0","1","2","3","4","5","6","7","8","9"));
        pals.addAll(res);
        return res;
      }
      ArrayList<String> res = new ArrayList<String>();
      ArrayList<String> smaller = genPals(val-2);
      for(String s: smaller){
          for(int i = 0; i <= 9; i++){
              res.add(addDig(s,i));
          }
      }
        pals.addAll(res);
        return res;
  }

  public static boolean isPrime(int t){
      int max = (int)Math.floor(Math.sqrt(t));
      for(int i = 2; i <= max; i++){
          if(t%i == 0){
              return false;
          }
      }
      return true;
  }

  public static String addDig(String pal, int dig){
      //add single digit to front and end of pal
      String d = Integer.toString(dig);
      return d+pal+d;
  }
}
