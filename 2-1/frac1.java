/*
ID: dennis.8
LANG: JAVA
TASK: frac1
*/
import java.io.*;
import java.util.*;


class frac1 {

public static ArrayList<String> pals = new ArrayList<String>();

    public static PrintWriter out;
public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
    out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
    int N = Integer.parseInt(f.readLine()); 
    ArrayList<Frac> fracs = new ArrayList<Frac>();
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <=i; j++){
            fracs.add(new Frac(j,i));
        }
    }
    fracs.add(new Frac(0,1));
    //keep only reduced fractions
    ArrayList<Frac> reduced = new ArrayList<Frac>();
    for(Frac r: fracs){
        if(r.num == 1 || r.den == 1){
            reduced.add(r);
        } else {
            boolean isReduced = true;
            for(int i = 2; i <= r.num; i++){
                if(r.num%i == 0 && r.den%i == 0){
                    isReduced = false;
                }
            }
            if(isReduced){
                reduced.add(r);
            }
        }
    }
    Collections.sort(reduced, new CustomComparator());
    for(Frac r: reduced){
        //System.out.println(r);
        out.println(r);
    }
    out.close();
  }

  static class Frac{
      int num;
      int den;
      double f;
      public Frac(int n, int d){
          this.num = n;
          this.den = d;
          this.f = 1.0*n/d;
      }
      public String toString(){
          return num+"/"+den;
      }
  }

  static class CustomComparator implements Comparator<Frac> {
    @Override
    public int compare(Frac f1, Frac f2) {
        double diff = f1.f - f2.f;
        return (int)Math.signum(diff);
    }
  }

}
