/*
ID: dennis.8
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

class crypt1 {

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
    int numDigits = Integer.parseInt(f.readLine());
    StringTokenizer st = new StringTokenizer(f.readLine());
    int[] digits = new int[numDigits];
    HashSet<Integer> allDigs = new HashSet<Integer>();
    for(int i = 0; i < numDigits; i++){
        int dig = Integer.parseInt(st.nextToken());
        digits[i] = dig;
        allDigs.add(dig);
    }
    int numCrypts = 0;
    for(int i = 0; i < numDigits; i++){
    for(int j = 0; j < numDigits; j++){
    for(int k = 0; k < numDigits; k++){
    for(int l = 0; l < numDigits; l++){
    for(int m = 0; m < numDigits; m++){
        boolean valid = tryCrypt(digits[i],digits[j],digits[k],digits[l],digits[m],allDigs);
        numCrypts += valid? 1 : 0;
    }
    }
    }
    }
    }
    //System.out.println(numCrypts);
    out.println(numCrypts);
    out.close();
  }

  public static boolean tryCrypt(int tl, int tm, int tr, int bl, int br, HashSet<Integer> digits){
      int top = tl*100 + tm*10 + tr;
      int bottom = bl*10+br;
      int partialTop = br*top;
      int partialBot = bl*top;
      int res = top*bottom;
      if(partialTop > 999 || partialTop < 100){
          return false;
      }
      if(partialBot > 999 || partialBot < 100){
          return false;
      }
      if(res > 9999 || res < 1000){
          return false;
      }
      return (inSet(partialTop,digits) 
              && inSet(partialBot,digits) && inSet(res,digits));
  }

  public static boolean inSet(int val, HashSet<Integer> digits){
      while(val > 0){
          int digit = val%10;
          if(!digits.contains(digit)){
              return false;
          }
          val /= 10;
      }
      return true;
  }

}
