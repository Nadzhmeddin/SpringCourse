package ru.geekbrains.java.newproject.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.geekbrains.java.newproject.model.Timesheet;

import java.util.Optional;

@Slf4j // - интерфейс для логирования
@Aspect
@Component
public class LoggingAspect {

    // AOP - аспектно-ориентированное программирование

    // Before
    // After
    // AfterThrowing
    // AfterReturning
    // Around

    @Pointcut("execution( * ru.geekbrains.java.newproject.service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointCut() {

    }

    // Pointcut - точка входа в аспект
//    @Before(value = "timesheetServiceMethodsPointCut()")
//    public void beforeTimesheetServiceFindById(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//      log.info("Before -> TimesheetService#{}", methodName);
//    }
//
//    @After(value = "timesheetServiceMethodsPointCut()")
//    public void afterTimesheetServiceFindById(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//        log.info("After -> TimesheetService#{}", methodName);
//    }


    @Before(value = "timesheetServiceMethodsPointCut()")
    public void beforeAllTimesheetsMethods(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object arg = jp.getArgs()[0];
        String type = arg.getClass().getTypeName();
        log.info("Before -> TimesheetService#{}({} = {})", methodName, type, arg);
    }
}
