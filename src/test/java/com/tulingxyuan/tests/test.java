package com.tulingxyuan.tests;

import com.tulingxyuan.service.Impl.UserServiceImpl;
import com.tulingxyuan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ChenCF on 2021/10/10
 */
public class test {

    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext  ioc =new ClassPathXmlApplicationContext("classpath:spring_ioc.xml");
        //当代理类实现了接口默认会使用jdk动态代理
        UserService bean = ioc.getBean(UserService.class);
        //如果使用如下接口的实现类去ioc容器里面就会报错  因为这个接口已经被aop代理类，相当于在ioc容器里面被aop修改类一样，如果需要获取
        //要么通过接口 要么通过名称获取  下面这样的会报错
        //UserServiceImpl bean = ioc.getBean(UserServiceImpl.class);
        System.out.println(bean.getClass());
        bean.delete(1);

    }
}
