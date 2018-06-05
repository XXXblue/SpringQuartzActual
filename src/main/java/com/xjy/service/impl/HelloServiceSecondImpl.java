package com.xjy.service.impl;

import com.xjy.service.HelloService;
import com.xjy.service.HelloServiceSecond;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/518:56
 * @Description:
 * @Modified By:
 */
@Service("helloServiceSecond")
@Slf4j
public class HelloServiceSecondImpl implements HelloServiceSecond {

    public void sayHello(String id) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        log.info(df.format(new Date()));
        log.info("hello============="+id);
    }
}
