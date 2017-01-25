/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MwanafunziProgress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author mwamb
 */
public class IDGenerator {
 //    CURRENT DATE
Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);
int micro;
String yr,mnth,dater,hr,mn,sc,action="";
String date2="",month2="";

 //RANDOM NUMBER GENERATOR
    Random random = new Random();
    
    public String current_id(){
 getMins();
 long fraction = (long) ((98725 - 219 ) * random.nextDouble());  
 String rand=Double.toString(fraction);
 String tableID=(rand).replace(".", ""); 
 String id=year+""+month+""+date+""+hour+""+min+""+sec+""+micro+""+tableID;       
       return  id;
    }
    
    public int getDays(String startDate,String endDate) throws ParseException{
Date d1 = new SimpleDateFormat("yyyy-M-dd").parse(startDate);
Date d2 = new SimpleDateFormat("yyyy-M-dd").parse(endDate);
    
long diff = Math.abs(d1.getTime() - d2.getTime());
int days = (int) (diff / (24 * 60 * 60 * 1000));



    return days;
    }
    
    public String timestamp(){
getMins();
 String id=year+"-"+month+"-"+date+" "+hour+":"+min+":"+sec+"."+micro;       
       return  id;
    }
    
    private void getMins(){     
min=cal.get(Calendar.MINUTE);
sec=cal.get(Calendar.SECOND);
micro=cal.get(Calendar.MILLISECOND);    
    }
    public String toDay(){
        if(date<10){
    date2="0"+date;
}
  if(date>=10){
    date2=""+date;
}
   String full_date=year+"-"+month+"-"+date2;     
   return full_date;
    }
 public int getCurrentYear(){
int current_year=Calendar.getInstance().get(Calendar.YEAR);
return current_year;
}
 
 public int getCurrentTerm(){
     int term_id=0;
   if(month>0 && month<=4) {term_id=1;} 
   if(month>4 && month<=8) {term_id=2;} 
   if(month>8 && month<=12) {term_id=3;} 
   
   return term_id;
 }
 public String getTime(){
     String time,title="AM";
     if(hour>=12){title="PM";}
     if(hour>12){hour-=12;}
          time=hour+":"+min+" "+title;
     return time;
 }
}



