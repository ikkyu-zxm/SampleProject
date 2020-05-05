package cn.ikkyu.ftp.annotations;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class MethodParameterResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {

        return methodParameter.getParameter().getAnnotation(ParameterData.class)!=null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {


        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        String data = request.getParameter("data");
        Class<?> parameterType = methodParameter.getParameterType();
        return JSONObject.parseObject(data,parameterType);
//        String str= requestWrapper.getRequest().getParameter("data");

//        return JSON.parseObject(str,methodParameter.getParameterType());
    }
}