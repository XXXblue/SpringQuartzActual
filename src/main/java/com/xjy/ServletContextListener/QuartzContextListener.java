package com.xjy.ServletContextListener;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/316:24
 * @Description: 让quartz线程池在tomcat关闭之前关闭，web.xml中的监听
 * @Modified By:
 */
@Slf4j
public class QuartzContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try{
            Scheduler startQuartz = (Scheduler) WebApplicationContextUtils
                    .getWebApplicationContext(event.getServletContext())
                    .getBean("startQuartz");
            if(startQuartz != null){
                startQuartz.shutdown(true);
            }
            Thread.sleep(1000);//主线程睡眠1s
        }catch (Exception e){
            if(!(e instanceof NoSuchBeanDefinitionException)){
                e.printStackTrace();
            }
        }
        event.getServletContext().log("QuartzContextListener销毁成功！");
        log.info("QuartzContextListener销毁成功！");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.info("QuartzContextListener启动成功！");
        event.getServletContext().log("QuartzContextListener启动成功！");
    }
}
