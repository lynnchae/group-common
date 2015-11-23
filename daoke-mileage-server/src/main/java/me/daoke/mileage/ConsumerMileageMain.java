package me.daoke.mileage;

import me.daoke.mileage.common.config.ApplicationContextLoader;
import me.daoke.mileage.filter.GPSDataFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 消费队列消息
 * User: chenlong
 * Date: 2014/12/23
 * Time: 11:59
 */
public class ConsumerMileageMain {

    private static Logger log = LoggerFactory.getLogger(ConsumerMileageMain.class);


    private static ApplicationContextLoader loader;


    private void initSpring() {
        loader = ApplicationContextLoader.getInstance();
    }


    public static void main(String args[]) {

        ConsumerMileageMain consumerMain =new ConsumerMileageMain();
        consumerMain.initSpring();
        GPSDataFilter gpsDataFilter = new GPSDataFilter(loader);
        gpsDataFilter.calMileage();


    }


}
