package me.daoke.driving.testrule;

import me.daoke.driving.util.CalDistanceUtil;
import me.daoke.driving.util.CalUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: chenlong
 * Date: 2015/5/20
 * Time: 14:56
 */
public class Test1 {
    public static void main(String args[]) throws ParseException {

                int i=4;
       // System.out.println(i-= i);
        //System.out.println(i-= i*=i);
        System.out.println(i-= i*=i+=i);

        System.out.println("days:" + CalUtil.getAllDays());
     //   System.out.println((10+10) /2 *2);

//        String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(1433317898 * 1000)) ;
//
//        Calendar cal =  Calendar.getInstance();
//        cal.setTimeInMillis(1433317898);
//
//        System.out.println(date);
//
//        System.out.println( 12001500 / 10000);
//        System.out.println( 12001500 % 100000);
//        long startTime = System.currentTimeMillis();
//        String  hourStr ="12001500";
//       // long startTime = System.nanoTime();
//
//        for (long i = 0; i < 100000; i++) {
//            // int a = 12001500 / 10000;
//             //int b = 12001500 % 10000;
////            String hs[] = hourStr.split("\\-");
////            hs[0].substring(0,2);
////            hs[1].substring(0,2);
//            int a = 0024 / 100;
//            int b = 114 % 100;
//        }
//        System.out.println("use time :" + (System.currentTimeMillis() - startTime) );

    }
}
