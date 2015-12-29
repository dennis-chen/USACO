/*
ID: dennis.8
LANG: JAVA
TASK: palsquare
*/
import java.io.*;
import java.util.*;

class palsquare {

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
    int base = Integer.parseInt(f.readLine());
    for(int i = 1; i < 301; i++){
        String squaredB = convBase((int)Math.pow(i,2), base);
        if(isPalindrome(squaredB)){
            String iB = convBase(i, base);
            out.println(iB + " " + squaredB);
        }
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
