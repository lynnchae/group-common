package me.daoke.poweroff.filter;

import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by jason on 2014/11/25.
 * <p/>
 * 请求日志拦截器
 */
public class LogSpringActionHandler extends HandlerInterceptorAdapter {

    private Logger log = org.slf4j.LoggerFactory.getLogger(LogSpringActionHandler.class);

    private StopWatch stopWatch = null;

    private String action = "";


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
          //记录用户操作行为日志

        //根据日志级别判断是否打印日志
        if (log.isInfoEnabled()) {
            action = request.getRequestURL().toString();
            Map map = request.getParameterMap();
            StringBuilder buf = new StringBuilder();
            for (Object key : map.keySet()) {
                buf.append(key).append("=").append(request.getParameter(key.toString())).append("&");
            }
            if (log.isInfoEnabled()) {
                 log.info(request.getRequestURL().toString() + "?" + buf.toString());
                this.stopWatch = new Log4JStopWatch();
            }
        }

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        if (log.isInfoEnabled()) {
            stopWatch.stop(this.action);
        }
        super.postHandle(request, response, handler, modelAndView);
    }


}
