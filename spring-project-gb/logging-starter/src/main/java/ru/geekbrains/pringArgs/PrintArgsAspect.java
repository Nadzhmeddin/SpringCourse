package ru.geekbrains.pringArgs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
@RequiredArgsConstructor
public class PrintArgsAspect {

    private final PrintArgsProperties properties;

    @Pointcut("@annotation(ru.geekbrains.pringArgs.PrintArgs)") // method
    public void printArgsMethodsPointcut() {}


    @Before(value = "printArgsMethodsPointcut()")
    public void printArgsMethods(JoinPoint jp) {
        StringBuilder message = new StringBuilder();

        String methodName = jp.getSignature().getName();
        String simpleName = jp.getTarget().getClass().getSimpleName();

        message.append("Before -> ")
                .append(simpleName)
                .append("->")
                .append(methodName)
                .append(":");

        if(properties.isPrintArgs()) {
            Object[] args = jp.getArgs();
            for (int i = 0; i < args.length; i++) {
                message.append(args[i].getClass().getSimpleName())
                        .append(" ")
                        .append(args[i]);
            }
        }
        log.info(message.toString());
    }

}
