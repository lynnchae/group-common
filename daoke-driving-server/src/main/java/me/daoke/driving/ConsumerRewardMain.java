package me.daoke.driving;

import me.daoke.driving.common.config.ApplicationContextLoader;
import me.daoke.driving.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: chenlong
 * Date: 2015/6/2
 * Time: 15:58
 */
public class ConsumerRewardMain {

    private static Logger log = LoggerFactory.getLogger(ConsumerMileageMain.class);


    private static ApplicationContextLoader loader;


    private void initSpring() {
        loader = ApplicationContextLoader.getInstance();
    }


    public static void main(String args[]) {

        ConsumerRewardMain consumerMain = new ConsumerRewardMain();
        consumerMain.initSpring();

        final PropertiesUtil props = PropertiesUtil.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.shutdown();

    }
}
