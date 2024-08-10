package ru.geekbrains.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j // - интерфейс для логирования
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {

    private final LoggingProperties properties;

    @Pointcut("@annotation(ru.geekbrains.aspect.Logging)") // method
    public void loggingMethodsPointcut() {}

    @Pointcut("@within(ru.geekbrains.aspect.Logging)") // class
    public void loggingTypePointcut() {}

    @Around(value = "loggingMethodsPointcut() || loggingTypePointcut()")
    public Object loggingMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        log.atLevel(properties.getLevel()).log("Before -> TimesheetService#{}", methodName);
        try {
            return pjp.proceed();
        } finally {
            log.atLevel(properties.getLevel()).log("After -> TimesheetService#{}", methodName);
        }
    }


}
