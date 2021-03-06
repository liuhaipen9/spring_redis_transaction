package com.example.eureka_client.aop;

import com.example.eureka_client.annotion.DefaultTranscantional;
import com.example.eureka_client.client.OrderClient;
import com.example.eureka_client.dto.OrderDto;
import com.example.eureka_client.dto.TranscationGroupDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/11下午9:42
 */
@Aspect
@Component
public class TranscationalAop {
    @Autowired
    private RedisTemplate<String,OrderDto> redisTemplate;

    @Autowired
    private OrderClient orderClient;

    @Pointcut("@annotation(com.example.eureka_client.annotion.DefaultTranscantional)")
    public void defaultTranscantional() {
    }

    @Pointcut("execution(* com.example.eureka_client.service.impl.OrderServiceImpl" +
            ".*(..))")
    public void excudeService() {
    }


    @Pointcut("@annotation(defaultTranscantional)")
    public void serviceDefaultTranscantional(DefaultTranscantional defaultTranscantional) {
    }



    @Around(value = "excudeService()")
    public Object defaultTranscantional(ProceedingJoinPoint joinPoint) {
        Object result = null;
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        //执行目标方法
        try {
            //前置通知
            System.out.println("The method " + methodName + " begins with " + Arrays.asList(joinPoint.getArgs()));
            //执行目标方法
            //开始事务
            //redisTemplate.multi();
            result=joinPoint.proceed();
            //后置通知
            System.out.println("The method " + methodName + "  ends with " + result);
            TranscationGroupDto transcationGroupDto=new TranscationGroupDto();
            transcationGroupDto.setGroupId("1");
            transcationGroupDto.setIndex("end");
            transcationGroupDto.setStatus("commit");
            String txRes=orderClient.commitTranscation(transcationGroupDto);
            if (!txRes.equals("commit")){
                //回滚事务
                throw new RuntimeException("事务异常异常");
            }
            //提交事务
            //redisTemplate.exec();
        } catch (Throwable e) {
            TranscationGroupDto transcationGroupDto=new TranscationGroupDto();
            transcationGroupDto.setGroupId("1");
            transcationGroupDto.setIndex("end");
            transcationGroupDto.setStatus("rollback");
            orderClient.commitTranscation(transcationGroupDto);
            //回滚事务
            //异常通知
            System.out.println("The method " + methodName + "  occurs exception: " + e);
            throw new RuntimeException(e);
        }
        //后置通知
        System.out.println("The method " + methodName + " ends");
        return result;
    }
}