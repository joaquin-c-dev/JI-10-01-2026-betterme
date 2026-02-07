package dev.joaquincoronado.betterme.shared.aspect;

import dev.joaquincoronado.betterme.shared.exception.BadRequestException;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import dev.joaquincoronado.betterme.user.usecase.UserUseCase;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Log
//@Aspect
@Component
public class ExampleAspect {

    //POINTCUTS
    @Pointcut("execution(public dev.joaquincoronado.betterme.user.model.BettermeUser getUserByIdUseCase(long))")
    public void getUserByIdUseCase(){}

    //ADVICES
    @Before("execution(public dev.joaquincoronado.betterme.user.model.BettermeUser getUserByIdUseCase(long))")
    public void before(JoinPoint joinPoint){
        log.info("Advice @Before - args: ["+joinPoint.getArgs()[0]+"]");
    }

    @AfterReturning(pointcut = "getUserByIdUseCase()", returning = "user")
    public void afterReturning(JoinPoint joinPoint, BettermeUser user){
        Object[] args = joinPoint.getArgs();
        Long userIdArgs = (Long) args[0];

        user.setEmail(user.getEmail()+"-modified");
        user.setPassword(null);
        String userEmail = user.getEmail();
        log.info("Advice @AfterReturning - return: ["+userEmail+"], args: ["+userIdArgs+"]");
    }

    @AfterThrowing(pointcut = "getUserByIdUseCase()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception){
        Object[] args = joinPoint.getArgs();
        Long userIdArgs = (Long) args[0];
        log.info("Advice @AfterThrowing - exception: ["+exception.getMessage()+"], args: ["+userIdArgs+"]");
    }

    @After("getUserByIdUseCase()")
    public void after(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Long userIdArgs = (Long) args[0];
        log.info("Advice @After/finally - args: ["+userIdArgs+"]");
    }

    @Around("getUserByIdUseCase()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("Advice @Around - start");
        Object object = null;
        try{
            object = joinPoint.proceed();
        } catch (Throwable e) {
            //throw new BadRequestException(e.getMessage());
            throw e;
        } finally {
            log.info("Advice @Around - end");
        }
        return object;
    }

    //JOIN POINT DESIGNATORS
    @Before("within(dev.joaquincoronado.betterme.user.usecase.UserUseCase)")
    public void withinClass(JoinPoint joinPoint){
        log.info("I'm in UserUseCase class: " + joinPoint.getSignature().getName()) ;
    }

    @Before("this(dev.joaquincoronado.betterme.user.dao.IUserDao)")
    public void pointcutDesignatorThis(JoinPoint joinPoint){
        log.info("I'm applying - IUserDao Interface:  " + joinPoint.getSignature().getName());
    }

    @Before("@within(org.springframework.stereotype.Service)")
    public void pointcutDesignatorAnnotations(JoinPoint joinPoint){
        log.info("I'm using @Service:  " + joinPoint.getSignature().getName());
    }
}
