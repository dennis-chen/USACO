/*
ID: dennis.8
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.util.*;

class dualpal {

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());
    int palsFound = 0;
    int numToCheck = S+1;
    while(palsFound < N){
        int palCount = 0;
        for(int base = 2; base < 11; base++){
            String newB = convBase(numToCheck,base);
            if(isPalindrome(newB)){
                palCount++;
            }
        }
        if(palCount > 1){
            out.println(numToCheck);
            palsFound++;
        }
        numToCheck++;
    }
    out.close();
  }

  public static String convBase(int num, int base){
      int power = 0;
      while(Math.pow(base,power) <= num){
          power++;
      }
      char[] res = new char[power];
      for(int i = power-1; i > -1; i--){
          int quotient = num/((int)Math.pow(base,i));
          int remainder = num%((int)Math.pow(base,i));
          res[power-1-i] = Character.toUpperCase(Character.forDigit(quotient,base));
          num = remainder;
      }
      return new String(res);
  }

  public static boolean isPalindrome(String s){
      int left = 0;
      int right = s.length()-1;
      while(left < right){
          if(s.charAt(left) != s.charAt(right)){
              return false;
          }
          left++;
          right--;
      }
      return true;
  }

}
