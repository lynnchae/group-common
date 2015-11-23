package me.daoke;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import me.daoke.common.mq.util.JsonMapper;
import me.daoke.mileage.common.model.CommonJsonResult;
import me.daoke.mileage.util.HttpRequester;
import me.daoke.mileage.util.HttpRespons;
import me.daoke.mileage.util.ParameterUtil;
import me.daoke.mileage.util.SHASignature;

import java.io.*;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }


    @org.junit.Test
    public void testHttp() throws IOException {
        Map<String, String> param = new HashMap<String, String>(6);
        param.put("appKey", "184269830");
        param.put("secret", "931E498698AB2D9B1D93F419E572D2ACCA981488");
        param.put("longitude", "121.3555438");
        param.put("latitude", "37.5375358");
        // param.put("accountID", "lu64o3ul5m");
        // param.put("accountID", "");
        param.put("direction", "0");
        param.put("speed", "20");
        param.put("altitude", "0");
        param.put("field", "PC|PN|CD|CA|CC|CN|RO|RN");
        //签名
        String sign = SHASignature.sign(ParameterUtil.getSignData(param));
        param.put("sign", sign);
        HttpRequester requester = new HttpRequester();
        HttpRespons respons = requester.sendPost("http://api.daoke.io/mapapi/v3/pointMatchRoad", param);
        Map<String, String> resParam = null;
        Map model = new HashMap();
        if (respons.getCode() == 200) {
            String content = respons.getContent();
            CommonJsonResult jsonResult1 = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
            if (jsonResult1.getERRORCODE().equals("0")) {
                System.out.println("result: " + jsonResult1.getRESULT());
            }
            //  System.out.println("jsonResult1: "+ jsonResult1);
        }
    }


    @org.junit.Test
    public void testReadFile() throws IOException {
        System.out.println("aaaaa");
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("e:/a.txt")));//构造一个BufferedReader类来读取文件
            String s = null;
           StringBuilder builder = new StringBuilder();
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                //result = result + "\n" + s;
                builder.append(s).append("\n");
            }
            br.close();
            //System.out.println("builder:"+builder.toString());

            List<Double[]> list =new ArrayList<Double[]>();
            Map map = (Map) JsonMapper.fromJson(builder.toString(),Map.class);
            if(map != null){
                List<Map> features = (List)map.get("features");
                for (Map map1 : features){
                       if(map1 != null){
                           Map map2 = (Map)map1.get("geometry");
                           if(map2 != null){
                               List<Double>  coordinates = (List<Double>)map2.get("coordinates");

                               Double[] aa = new Double[]{coordinates.get(0),coordinates.get(1)};

                               list.add(aa);
                           }
                       }
                }
            }
            System.out.print("list:" + list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* FileInputStream fileInputStream = new FileInputStream(new File("e:/e46(1).json"));
        char[] tempchars = new char[30];
        int charread = 0;
        InputStreamReader reader = new InputStreamReader(fileInputStream);
        while ((charread = reader.read(tempchars)) != -1){
            // 同样屏蔽掉\r不显示
            if ((charread == tempchars.length)
                    && (tempchars[tempchars.length - 1] != '\r')) {
                System.out.print(tempchars);
            } else {
                for (int i = 0; i < charread; i++) {
                    if (tempchars[i] == '\r') {
                        continue;
                    } else {
                        System.out.print(tempchars[i]);
                    }
                }
            }
        }
        reader.close();*/

    }

    @org.junit.Test
    public void testCale(){




        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTimeInMillis() /(24*60*60*1000*7));
        System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
        //cal.set(Calendar.MONDAY,3);
        System.out.println(cal.get(Calendar.MONTH));

       /* System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
*/
        System.out.println(78000123/100000);

        System.out.println(78000123%100000);




    }
}
