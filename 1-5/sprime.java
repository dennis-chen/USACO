/*
ID: dennis.8
LANG: JAVA
TASK: sprime
*/
import java.io.*;
import java.util.*;


class sprime {

public static ArrayList<String> pals = new ArrayList<String>();

    public static PrintWriter out;

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
    out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
    int ribNum = Integer.parseInt(f.readLine()); 
    findSuperPrimes(2, 1, ribNum);
    findSuperPrimes(3, 1, ribNum);
    findSuperPrimes(5, 1, ribNum);
    findSuperPrimes(7, 1, ribNum);
    out.close();
  }

  public static void findSuperPrimes(int val, int len, int maxLen){
      if(!isPrime(val)){
          return;
      }
      if(len == maxLen){
          out.println(val);
          return;
      }
      for(int i = 1; i <= 9; i++){
          findSuperPrimes(val*10+i,len+1,maxLen);
      }
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
  
}
