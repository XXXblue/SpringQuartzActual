package com.xjy.service.impl;

import com.xjy.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/311:07
 * @Description:
 * @Modified By:
 */

/**
 * 这个是任务别乱删
 */
@Service("helloService")
@Slf4j
public class HelloServiceImpl implements HelloService{

    public void sayHello() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        log.info(df.format(new Date()));
        log.info("hello=============");
    }
}
