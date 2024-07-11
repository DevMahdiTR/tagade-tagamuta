package tagarde.logging;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* tagarde.core.service..*.*(..))")
    public void serviceMethods() {}

    @Pointcut("execution(* tagarde.core.rest..*.*(..))")
    public void controllerMethods() {}

    @Before("serviceMethods() || controllerMethods()")
    public void logMethodEntry(@NotNull JoinPoint joinPoint) {
        log.debug("Entering method: {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "serviceMethods() || controllerMethods()", returning = "result")
    public void logMethodExit(@NotNull JoinPoint joinPoint, Object result) {
        log.debug("Exiting method: {}. Returned: {}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(pointcut = "serviceMethods() || controllerMethods()", throwing = "exception")
    public void logException(@NotNull JoinPoint joinPoint, @NotNull Throwable exception) {
        log.error("Exception in method: {}. Exception: {}", joinPoint.getSignature().toShortString(), exception.getMessage());
    }

    @Around("serviceMethods() || controllerMethods()")
    public Object logExecutionTime(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.debug("Execution time of {} took {} ms", joinPoint.getSignature().toShortString(), (endTime - startTime));
        return proceed;
    }

}
