package me.daoke.driving;

import me.daoke.driving.common.config.ApplicationContextLoader;
import me.daoke.driving.filter.GPSDataFilter;
import me.daoke.driving.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

        ConsumerMileageMain consumerMain = new ConsumerMileageMain();
        consumerMain.initSpring();

        final PropertiesUtil props = PropertiesUtil.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        final GPSDataFilter gpsDataFilter = new GPSDataFilter(loader);

        executorService.execute(new Runnable() {

            @Override
            public void run() {
                gpsDataFilter.calMileage(props.getProperty("gpsdata_queue"));
            }
        });
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                gpsDataFilter.calMileage(props.getProperty("gpsdata_queue1"));
            }
        });
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                gpsDataFilter.calMileage(props.getProperty("gpsdata_queue2"));
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                gpsDataFilter.calMileage(props.getProperty("gpsdata_queue3"));
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                gpsDataFilter.calMileage(props.getProperty("gpsdata_queue4"));
            }
        });

        executorService.shutdown();

    }


}
