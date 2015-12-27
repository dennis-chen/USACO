/*
ID: dennis.8
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;

class friday {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("friday.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
    int years = Integer.parseInt(f.readLine());
    Date d = new Date(Month.JAN, Day.MONDAY, 1, 1900);
    int[] thirteenthCount = new int[7];
    while(d.year < 1900 + years){
        if(d.date == 13){
            thirteenthCount[d.day.ordinal()]++;
        }
        d.incrementOne();
    }
    for(int i = 0; i < thirteenthCount.length; i++){
        out.print(thirteenthCount[i]);
        if(i != thirteenthCount.length - 1){
            out.print(" ");
        }
    }
    out.print("\n");
    out.close();
  }

  static enum Day {
      SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, 
      FRIDAY {
        @Override
        public Day next() {
            return Day.SATURDAY;
        }; 
      };
      public Day next(){
          return values()[ordinal() + 1];
      }
  }

  static enum Month {
      JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, 
      DEC {
        @Override
        public Month next() {
            return Month.JAN;
        }; 
      };

      public Month next(){
          return values()[ordinal() + 1];
      }
  }

  static class Date {
      Day day;
      Month month;
      int date;
      int year;
      public Date(Month m, Day d, int date, int year){
          this.month = m;
          this.day = d;
          this.date = date;
          this.year = year;
      }

      public String toString(){
          return month.name() + " " + date + " " + year + " " + day.name();
      }

      public static boolean isLeapYear(int year){
          if(year % 100 == 0){
              if(year % 400 == 0){
                  return true;
              }
              return false;
          }
          return (year % 4 == 0);
      }

      public void incrementOne(){
        this.day = this.day.next();
        this.date++;
        if(month == Month.SEP ||
           month == Month.APR ||
           month == Month.JUN ||
           month == Month.NOV ){
            if(this.date == 31){
                this.month = this.month.next();
                this.date = 1;
            }
         } else if (month == Month.FEB) {
             if(Date.isLeapYear(this.year)){
                if(this.date == 30){
                    this.month = this.month.next();
                    this.date = 1;
                }
             } else {
                if(this.date == 29){
                    this.month = this.month.next();
                    this.date = 1;
                }
             
             }
         } else if (month == Month.DEC){
            if(this.date == 32){
                this.month = this.month.next();
                this.date = 1;
                this.year++;
            }
         } else {
            if(this.date == 32){
                this.month = this.month.next();
                this.date = 1;
            }
         }
      }
  }
}
