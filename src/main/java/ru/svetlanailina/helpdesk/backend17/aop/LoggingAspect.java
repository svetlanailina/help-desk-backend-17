package ru.svetlanailina.helpdesk.backend17.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Log
public class LoggingAspect {
    //аспект будет выполняться для всех методов из пакета controller
    @Around("execution(* ru.svetlanailina.backend.helpdesk.controller..*(..)))")
    public Object profileControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // считываем метаданные - что сейчас выполняется
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        // получаем информацию о том, какой класс и метод выполняется
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        log.info("-------- Executing " + className + "." + methodName + "   ----------- ");

        StopWatch countdown = new StopWatch();
        countdown.start();
        Object result = proceedingJoinPoint.proceed(); // выполнить метод
        countdown.stop();

        log.info("-------- Execution time of " + className + "." + methodName + " :: " + countdown.getTotalTimeMillis() + " ms");

        return result;
    }
}
