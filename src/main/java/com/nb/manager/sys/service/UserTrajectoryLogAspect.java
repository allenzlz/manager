package com.nb.manager.sys.service;

import com.nb.manager.sys.entity.UserTrajectory;
import com.nb.manager.sys.mapper.UserTrajectoryMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 定义切面
 * zhangqiang
 */
@Aspect
@Component
public class UserTrajectoryLogAspect {

    @Autowired
    private UserTrajectoryService userTrajectoryService;
    // 定义切点
    @Pointcut("@annotation(com.nb.manager.sys.service.UserTrajectoryLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable, InterruptedException {
        System.out.println("=============================");
        UserTrajectory userTrajectory = new UserTrajectory();
        Object result = point.proceed();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String name = method.getName();
        String[] parameterNames = signature.getParameterNames();
        Parameter[] Parameter = method.getParameters();
        System.out.println("Method Name:" + name);//输出方法名
        UserTrajectoryLog log = method.getAnnotation(UserTrajectoryLog.class);
        System.out.println("Log Value:" + log.value());//输出注解里面的值
        userTrajectory.setUserName("allen");
        userTrajectory.setOperationModule(name);
        userTrajectory.setOperationContent(log.value());
        userTrajectory.setOperationDate(new Timestamp(System.currentTimeMillis()));
        userTrajectoryService.save(userTrajectory);
        System.out.println("+++++++++++++++++++++++++++++");
        return result;
    }

}
