/*
ID: dennis.8
LANG: JAVA
TASK: preface
*/
import java.io.*;
import java.util.*;


class preface {

    public static PrintWriter out;
    public static int N;
    public static int[] letterCount = new int[7];
    public static char[] letters = new char[]{'I','V','X','L','C','D','M'};
    public static int[] fives = new int[]{1,3,5,-1};
    public static int[] ones = new int[]{0,2,4,6,-1};

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("preface.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
        //read input
        N = Integer.parseInt(f.readLine());
        for(int i = 1; i <= N; i++){
            countLetters(i);
        }
        for(int i = 0; i < letterCount.length; i++){
            if(letterCount[i] == 0){
                break;
            }
            System.out.print(letters[i]+" ");
            System.out.println(letterCount[i]);
            out.print(letters[i]+" ");
            out.println(letterCount[i]);
        }
        out.close();
    }

    public static void countLetters(int n){
        String s = Integer.toString(n);
        int dig = s.length()-1;
        countLetterHelper(s, dig);
        return;
    }

    public static void countLetterHelper(String s, int dig){
        if(dig < 0){
            return;
        }
        int index = s.length()-dig-1;
        int val = Character.getNumericValue(s.charAt(index));
        int one = ones[dig];
        int five = fives[dig];
        int ten = ones[dig+1];
        if(0 < val && val < 4){
            letterCount[one] += val;
        } else if (val == 4){
            letterCount[one] += 1;
            letterCount[five] += 1;
        } else if (4 < val && val < 9){
            letterCount[five] += 1;
            letterCount[one] += (val-5);
        } else if (val == 9){
            letterCount[one] += 1;
            letterCount[ten] += 1;
        }
        countLetterHelper(s,dig-1);
    }

}
