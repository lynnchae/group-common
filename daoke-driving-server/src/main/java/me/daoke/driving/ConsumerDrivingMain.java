package me.daoke.driving;

import me.daoke.driving.common.config.ApplicationContextLoader;
import me.daoke.driving.filter.PowerOffFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 消费队列消息
 * User: chenlong
 * Date: 2014/12/23
 * Time: 11:59
 */
public class ConsumerDrivingMain {

    private static Logger log = LoggerFactory.getLogger(ConsumerDrivingMain.class);

    private static ApplicationContextLoader loader;


    private void initSpring() {
        loader = ApplicationContextLoader.getInstance();
    }

    public static void main(String args[]) throws Exception {
        ConsumerDrivingMain consumerMain = new ConsumerDrivingMain();
        consumerMain.initSpring();
        PowerOffFilter powerOffFilter = new PowerOffFilter(loader);

        powerOffFilter.calPowerOff();


    }


}
