package com.tulingxyuan.aspect;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Created by ChenCF on 2021/10/10
 */
@Aspect      //标记为切面
@Component   //标记为bean   才能切入ioc里面   交给ioc容器去管理
public class LogAspect {


    //可以采用  声明切点的方式 如其他通知引用     相当于在com.tulingxyuan.service.Impl包下面的所有的方法进行增强日志信息（相当于从哪个类开始）
    @Pointcut("execution(* com.tulingxyuan.service.Impl..*.*(..))")
    public void pointCut() {
    }


    //前置通知     切点表达式
    // @Before("execution(* com.tulingxyuan.service.Impl..*.*(..))")
    //加@annotation(ApiOperation)就可以在swagger获取value名称   就可以匹配方法上面有ApiOperation这个的注解的name名称，也可以进行事务管理
    @Before("pointCut() && @annotation(logger)&&@annotation(transactional)")
    public void before(JoinPoint joinPoint, Logger logger, Transactional transactional) {
        //通过joinPoint获取方法名称 参数信息
        //获取方法名称
        Signature name = joinPoint.getSignature();
        //获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println(name + "方法运行了，参数为：" + Arrays.asList(args) + "可以获得注解名称为：" + logger.name());
        // System.out.println("前置通知");
    }

    //后置通知
    // @After("execution(* com.tulingxyuan.service.Impl..*.*(..))")
    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        //通过joinPoint获取方法名称 参数信息
        //获取方法名称
        Signature name = joinPoint.getSignature();
        //获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println(name + "方法运行了，参数为：" + Arrays.asList(args));
        //  System.out.println("后置通知");
    }

    //后置返回通知  //有的时候需要查看返回值类型
    //@AfterReturning(value = "execution(* com.tulingxyuan.service.Impl..*.*(..))", returning = "returnValue")
    @AfterReturning(value = "pointCut()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        Signature signature = joinPoint.getSignature();
        System.out.println(signature + "方法执行完成，返回结果：" + returnValue);
    }

    //后置异常通知   //需要直接可以查看异常
    // @AfterThrowing(value = "execution(* com.tulingxyuan.service.Impl..*.*(..))", throwing = "exception")
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        Signature signature = joinPoint.getSignature();
        //需要获取具体的异常栈信息
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter, true));
        String exTrace = stringWriter.getBuffer().toString();
        System.out.println(signature + "方法出现异常" + exTrace);
        //System.out.println(signature + "方法出现异常" + exception);
        // System.out.println("后置异常通知");
    }


    //TODO  环绕通知  在环绕通知里面获取方法名称不能使用joinPoint
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        //环绕通知里面获取方法名称
        Signature signature = proceedingJoinPoint.getSignature();
        //环绕通知里面获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        //就可以灵活写前置 后置 异常等等
        Object result = "";
        try {
            System.out.println("环绕前置通知 " + signature + "方法执行了，参数是：" + Arrays.asList(args));
            //TODO  需要自己去执行得到返回值
            result = proceedingJoinPoint.proceed(args);
            System.out.println("环绕返回通知 " + signature + "方法返回了，返回值是：" + result);
        } catch (Throwable throwable) {
            System.out.println("环绕异常通知 " + signature + "方法出现异常，异常信息：" + throwable);
        } finally {
            System.out.println("环绕后置通知 " + signature + "方法结束");
        }
        return result;
    }

}
