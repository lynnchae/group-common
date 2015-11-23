package me.daoke.poweroff;

import me.daoke.poweroff.entity.GPSDataProtuBuf;
import me.daoke.poweroff.util.CalHashUtil;

import java.util.List;

/**
 * User: chenlong
 * Date: 2015/6/13
 * Time: 9:53
 */
public class TestCal {


    public static void main(String args[]) {

        for (int i=0; i<50; i++)


         System.out.println( CalHashUtil.newCompatHashingAlg("EFIYOKEF"+i) %5);

       // System.out.println();
//        for (int j = 0; j < 10000000; j++) {
//
//
//            GPSDataProtuBuf.GPSData.Builder gpsData = GPSDataProtuBuf.GPSData.newBuilder();
//            GPSDataProtuBuf.Location.Builder location = null;
//            for (int i = 0; i < 10; i++) {
//                location = GPSDataProtuBuf.Location.newBuilder();
//                location.setLongitude(121.131);
//                location.setLatitude(31.45);
//                location.setSpeed(50);
//                location.setAltitude(9);
//                location.setDirection(63);
//                location.setGPSTime(1343543098 + i);
//                gpsData.addLocations(location);
//            }
//            TestCal testCal1 = new TestCal();
//            testCal1.quickList(gpsData.getLocationsList());
//        }


//        for (int i = 0; i < gpsData.getLocationsList().size(); i++) {
//            System.out.print(gpsData.getLocationsList().get(i));
//        }
//        System.out.println();


    }

    public int getMiddleList(List<GPSDataProtuBuf.Location> list, int low, int high) {
        GPSDataProtuBuf.Location tmp = list.get(low);    //数组的第一个作为中轴
        while (low < high) {
            while (low < high && list.get(high).getGPSTime() >= tmp.getGPSTime()) {
                high--;
            }
            list.set(low, list.get(high));   //比中轴小的记录移到低端
            while (low < high && list.get(low).getGPSTime() <= tmp.getGPSTime()) {
                low++;
            }
            list.set(high, list.get(low));
            // list[high] = list[low];   //比中轴大的记录移到高端
        }
        list.set(low, tmp);
        //list[low] = tmp;              //中轴记录到尾
        return low;                   //返回中轴的位置
    }

    /**
     * 递归形式的分治排序算法
     *
     * @param list
     * @param low
     * @param high
     */
    public void _quickSortList(List<GPSDataProtuBuf.Location> list, int low, int high) {
        if (low < high) {
            int middle = getMiddleList(list, low, high);  //将list数组进行一分为二
            _quickSortList(list, low, middle - 1);        //对低字表进行递归排序
            _quickSortList(list, middle + 1, high);       //对高字表进行递归排序
        }
    }

    /**
     * @param str
     */
    public void quickList(List<GPSDataProtuBuf.Location> str) {
        if (str.size() > 0) {    //查看数组是否为空
            _quickSortList(str, 0, str.size() - 1);
        }
    }


    public int getMiddle(Integer[] list, int low, int high) {
        int tmp = list[low];    //数组的第一个作为中轴
        while (low < high) {
            while (low < high && list[high] >= tmp) {
                high--;
            }
            list[low] = list[high];   //比中轴小的记录移到低端
            while (low < high && list[low] <= tmp) {
                low++;
            }
            list[high] = list[low];   //比中轴大的记录移到高端
        }
        list[low] = tmp;              //中轴记录到尾
        return low;                   //返回中轴的位置
    }

    /**
     * 递归形式的分治排序算法
     *
     * @param list
     * @param low
     * @param high
     */
    public void _quickSort(Integer[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);  //将list数组进行一分为二
            _quickSort(list, low, middle - 1);        //对低字表进行递归排序
            _quickSort(list, middle + 1, high);       //对高字表进行递归排序
        }
    }

    /**
     * @param str
     */
    public void quick(Integer[] str) {
        if (str.length > 0) {    //查看数组是否为空
            _quickSort(str, 0, str.length - 1);
        }
    }
}
