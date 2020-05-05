package cn.ikkyu.ftp;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author xinming
 */
@Aspect
@Component
public class TestAspect {



    @Pointcut(value = "@annotation(cn.ikkyu.ftp.annotations.DataParameterConvert)")
    public void idempotentMethod() {
    }

    @Around("idempotentMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);
        Class<?> parameterType = method.getParameterTypes()[0];

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String data = request.getParameter("data");
        Object o = JSONObject.parseObject(data, parameterType);
        return joinPoint.proceed(new Object[]{o});
    }


    /**
     * 获取当前执行的方法
     *
     * @param joinPoint 连接点
     * @return 方法
     */
    private Method getMethod(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            method = target.getClass().getMethod(method.getName(),
                    method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }
}
