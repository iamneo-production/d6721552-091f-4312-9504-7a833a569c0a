package com.teampheonix.tplanguagemanagementapi.aspect;

import com.teampheonix.tplanguagemanagementapi.exception.ApiErrorCodes;
import com.teampheonix.tplanguagemanagementapi.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class AuthorizeRolesAspect {

    @Pointcut("@annotation(com.teampheonix.tplanguagemanagementapi.aspect.AuthorizeRoles)")
    public void authorizeMethodPointCut() { }

    @Before(value = "authorizeMethodPointCut()")
    public void authorizeRequest(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AuthorizeRoles annotation = methodSignature.getMethod().getAnnotation(AuthorizeRoles.class);
        String[] allowedRoles = annotation.roles();
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            log.error("Aspect - Authorize Roles - Servlet Request Attributes are null");
            throw new ApiException(ApiErrorCodes.UNEXPECTED_ERROR);
        }
        HttpServletRequest request = requestAttributes.getRequest();
        validateRoles(request, allowedRoles);
    }

    private void validateRoles(HttpServletRequest request, String[] allowedRoles) {
        String claims = request.getHeader("CLAIMS");
        if (StringUtils.isBlank(claims)) {
            log.error("Aspect - Claims are blank");
            throw new ApiException(ApiErrorCodes.UNAUTHORIZED_ERROR);
        }
        String[] roles = claims.split(",");
        if(Arrays.stream(roles).noneMatch(role -> Arrays.stream(allowedRoles).anyMatch(role::equalsIgnoreCase))) {
            log.error("Aspect - Unauthorized access - Insufficient Access Role");
            throw new ApiException(ApiErrorCodes.UNAUTHORIZED_ACCESS_ERROR);
        }
    }

}
