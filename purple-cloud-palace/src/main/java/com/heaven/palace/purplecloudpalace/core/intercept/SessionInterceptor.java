package com.heaven.palace.purplecloudpalace.core.intercept;

import com.heaven.palace.purplecloudpalace.base.controller.BaseController;
import com.heaven.palace.purplecloudpalace.util.HttpSessionHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * 静态调用session的拦截器
 *
 * @author ZhouShengEn
 * @date 2022年8月25日
 */
@Aspect
@Component
public class SessionInterceptor extends BaseController {

    @Pointcut("execution(* com.heaven.palace.purplecloudpalace.*..controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {

        HttpSessionHolder.put(super.getHttpServletRequest().getSession());
        try {
            return point.proceed();
        } finally {
            HttpSessionHolder.remove();
        }
    }
}
