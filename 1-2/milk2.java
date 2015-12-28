/*
ID: dennis.8
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

class milk2 {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
    int farmerNum = Integer.parseInt(f.readLine());
    ArrayList<MilkTime> times = new ArrayList<MilkTime>();
    for(int i = 0; i < farmerNum; i++){
        StringTokenizer st = new StringTokenizer(f.readLine());
        int startTime = Integer.parseInt(st.nextToken());
        int endTime = Integer.parseInt(st.nextToken());
        times.add(new MilkTime(startTime, true));
        times.add(new MilkTime(endTime, false));
    }
    Collections.sort(times, new CustomComparator());
    int maxOneCowMilked = 0;
    int maxNoMilk = 0;
    Stack<MilkTime> milkBegins = new Stack<MilkTime>();
    MilkTime lastEnd = null;
    for(MilkTime m: times){
        MilkTime popped = null;
        if(m.start){
            if(milkBegins.isEmpty() && lastEnd != null){
                int noMilk = m.time - lastEnd.time;
                if(noMilk > maxNoMilk){
                    maxNoMilk = noMilk;
                }
            }
            milkBegins.push(m);
        } else {
            popped = milkBegins.pop();
            lastEnd = m;
        }
        if(milkBegins.isEmpty()){
            int oneCowMilked = m.time - popped.time;
            if(oneCowMilked > maxOneCowMilked){
                maxOneCowMilked = oneCowMilked;
            }
        }
    }
    out.println(maxOneCowMilked + " " + maxNoMilk);
    out.close();
  }

  static class MilkTime {
      int time;
      boolean start;
      public MilkTime(int t, boolean s){
          this.time = t;
          this.start = s;
      }
      public String toString(){
          return time + " " + start;
      }
  }

  static class CustomComparator implements Comparator<MilkTime> {
    @Override
    public int compare(MilkTime m1, MilkTime m2) {
        if(m1.time - m2.time == 0){
            return m1.start ? -1 : 1;
        } else {
            return m1.time - m2.time;
        }
    }
  }
}
