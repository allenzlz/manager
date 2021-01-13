package com.nb.manager.sys.service;

import com.alibaba.fastjson.JSON;
import com.nb.manager.sys.entity.SysUser;
import com.nb.manager.sys.entity.UserTrajectory;
import com.nb.manager.sys.mapper.UserTrajectoryMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        System.out.println("Method Name:" + name);//输出方法名
        UserTrajectoryLog log = method.getAnnotation(UserTrajectoryLog.class);
        System.out.println("Log Value:" + log.value());//输出注解里面的值
        if(name.contains("User")){
            userManagementOperation(name,point.getArgs(),userTrajectory,log.value());
        }

        return result;
    }

    /**
     * 用户管理相关操作记录处理类
     * @param methodName 方法名
     * @param params     参数内容
     * @param userTrajectory  操作日志对象
     * @param operationModule 操作类型
     */
    public void userManagementOperation(String methodName,Object[] params,UserTrajectory userTrajectory,String operationModule){
        //当前登录用户
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(methodName.equals("addUser") || methodName.equals("editUser")){
            SysUser sysUserOperation = (SysUser) params[0];
            userTrajectory.setUserId(sysUser.getId());
            userTrajectory.setOperationModule(operationModule);
            if(methodName.equals("addUser")){
                userTrajectory.setOperationContent("添加了用户ID为:"+sysUserOperation.getId()+"姓名为:"+sysUserOperation.getRealName()+"的用户");
            }else{
                userTrajectory.setOperationContent("修改了用户ID为:"+sysUserOperation.getId()+"姓名为:"+sysUserOperation.getRealName()+"的用户");
            }
            userTrajectory.setOperationDate(new Timestamp(System.currentTimeMillis()));
            userTrajectoryService.save(userTrajectory);
        }else if(methodName.equals("delUser")){
            int userId = (int) params[0];
            String userName = (String) params[1];
            userTrajectory.setUserId(sysUser.getId());
            userTrajectory.setOperationModule(operationModule);
            userTrajectory.setOperationContent("删除了用户ID为:"+userId+"姓名为:"+userName+"的用户");
            userTrajectory.setOperationDate(new Timestamp(System.currentTimeMillis()));
            userTrajectoryService.save(userTrajectory);
        }
    }
}
