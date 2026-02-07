package dev.joaquincoronado.betterme.shared.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Log
@Aspect
@Component
public class ChronometerAspect {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void services(){}

    @Pointcut("@within(org.springframework.stereotype.Repository)")
    private void dao(){}


    @Around("services() || dao()")
    public Object calculateTotalTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();

        Object response;

        try{
            response = joinPoint.proceed();
        } catch(Throwable e){
            throw e;
        } finally {
            Long endTime = System.currentTimeMillis();
            long diff = endTime - startTime;
            Signature signature  = joinPoint.getSignature();
            log.info(signature.getDeclaringTypeName() + "[" + signature.getName()+ "] " + "time: " + diff + " ms");
        }

        return response;
    }
}
