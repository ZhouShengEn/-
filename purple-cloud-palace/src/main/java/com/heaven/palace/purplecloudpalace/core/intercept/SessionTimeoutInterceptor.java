package com.heaven.palace.purplecloudpalace.core.intercept;

import com.heaven.palace.purplecloudpalace.base.controller.BaseController;
import com.heaven.palace.purplecloudpalace.core.shiro.ShiroKit;
import com.heaven.palace.purplecloudpalace.support.HttpKit;
import org.apache.shiro.session.InvalidSessionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 验证session超时的拦截器
 *
 * @author ZhouShengEn
 * @date 2022年8月25日
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "guns", name = "session-open", havingValue = "true")
public class SessionTimeoutInterceptor extends BaseController {

    @Pointcut("execution(* com.heaven.palace.purplecloudpalace.*..controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionTimeoutValidate(ProceedingJoinPoint point) throws Throwable {

        String servletPath = HttpKit.getRequest().getServletPath();

        if (servletPath.equals("/kaptcha") || servletPath.equals("/login") ||
                servletPath.equals("/register") || servletPath.equals("/global/sessionError")||
                servletPath.startsWith("/share")) {
            return point.proceed();
        } else {
            if (ShiroKit.getSession().getAttribute("sessionFlag") == null) {
                ShiroKit.getSubject().logout();
                throw new InvalidSessionException();
            } else {
                return point.proceed();
            }
        }
    }
}
