/*
ID: dennis.8
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;

class namenum {

  public static void main (String [] args) throws IOException {
    BufferedReader d = new BufferedReader(new FileReader("dict.txt"));
    HashSet<String> validNames = new HashSet<String>();
    String line;
    while((line = d.readLine()) != null){
        validNames.add(line);
    }
    BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
    HashMap<Character,String> keypad = new HashMap<Character,String>();
    keypad.put('2', "ABC");
    keypad.put('3', "DEF");
    keypad.put('4', "GHI");
    keypad.put('5', "JKL");
    keypad.put('6', "MNO");
    keypad.put('7', "PRS");
    keypad.put('8', "TUV");
    keypad.put('9', "WXY");
    String serialNum = f.readLine();
    BooleanHolder namesExist = new BooleanHolder();
    namesExist.value = false;
    genNames(keypad,validNames,out,namesExist,serialNum,"");
    if(!namesExist.value){
        out.println("NONE");
    }
    out.close();
  }

  public static class BooleanHolder {
      public Boolean value;
  }

  public static void genNames(HashMap<Character,String> k, HashSet<String> names,
          PrintWriter out, BooleanHolder namesExist, String serialNum, String initial){
      if(initial.length() == serialNum.length()){
          if(names.contains(initial)){
              out.println(initial);
              namesExist.value = true;
          }
          return;
      }
      Character c = serialNum.charAt(initial.length());
      String letters = k.get(c);
      for(int i = 0; i < letters.length(); i++){
          genNames(k,names,out,namesExist,serialNum,initial+letters.charAt(i));
      }
  }

}
