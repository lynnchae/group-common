package me.daoke.driving.cache;


import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangzp on 2015/4/2.
 */
public class LocalCache {
    public static Map<String,Map<String,Integer>> cacheMap = new HashMap<String,Map<String,Integer>>();

  /*  public boolean addBullshit (String accountID,String ruleID,Date date,int i){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateString =sdf1.format(date);
         Map<String,Integer> map = new HashMap<String, Integer>();
        String accountRule =   accountID+"_"+ruleID;
        if (cacheMap.get(accountRule)==null){
            cacheMap.put(accountRule,map);
        }
        map = cacheMap.get(accountRule);
       int count =  map.get(dateString);
        count +=i;
        map.put(dateString,count) ;
        return true;
    }*/


 /*   public boolean addBullshit (String accountID,String ruleID,Date date,int i){
        Map<String,Map<String,Map<String,String>>> map = new HashMap<String, Map<String, Map<String, String>>>();
        if (map.get(ruleID).get(accountID)==null){
            map.get(ruleID).put(accountID,new HashMap<String, String>());
        }
         String bullshitdate= map.get(ruleID).get(accountID).get("bullshitdate");

        if (bullshitdate==null){
            map.get(ruleID).get(accountID).put("number",)
        }
        return true;
    }*/

}
